package hu.mzsombor.jobmonitor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import hu.mzsombor.jobmonitor.config.JobmonitorConfigProperties;
import hu.mzsombor.jobmonitor.dto.ExternalApiResultsDto;
import hu.mzsombor.jobmonitor.model.Position;
import hu.mzsombor.jobmonitor.repository.PositionRepository;

@Service
public class PositionService {

	@Autowired
	JobmonitorConfigProperties config;

	@Autowired
	PositionRepository positionRepository;
	
	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	@Transactional
	public Position addNewPosition(Position position) {
		Position newPosition = positionRepository.save(position);
		newPosition.setUrl(config.getBaseUrl() + "/positions/" + newPosition.getId());
		return newPosition;
	}
	
	public Optional<Position> getPosition(long id) {
		return positionRepository.findById(id);
	}
	
	public List<Position> search(String title, String location) {
		List<Position> results = new ArrayList<>();
		results.addAll(searchInternalSource(title, location));
		results.addAll(searchExternalSource(title, location));
		return results;
	}
	
	private List<Position> searchInternalSource(String title, String location) {
		Specification<Position> spec = Specification.where(null);
		if (StringUtils.hasText(title))
			spec = spec.and(PositionSpecifications.hasKeyword(title));
		if (StringUtils.hasText(location))
			spec = spec.and(PositionSpecifications.hasLocation(location));
		
		return positionRepository.findAll(spec);
	}
	
	private List<Position> searchExternalSource(String title, String location) {
		if (title == null)
			title = "";
		if (location == null)
			location = "";

		RestTemplate restTemplate = restTemplateBuilder.basicAuthentication(config.getExternalApiKey(), "").build();
		ExternalApiResultsDto result = restTemplate.getForObject(
				config.getExternalApiUrl() + "?keywords=" + title + "&locationName=" + location,
				ExternalApiResultsDto.class);

		return result
				.getResults()
				.stream()
				.map(r -> new Position(r.getJobTitle(), r.getLocationName(), r.getJobUrl()))
				.collect(Collectors.toList());
	}

}
