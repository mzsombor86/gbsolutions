package hu.mzsombor.jobmonitor.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.mzsombor.jobmonitor.config.JobmonitorConfigProperties;
import hu.mzsombor.jobmonitor.model.Position;
import hu.mzsombor.jobmonitor.repository.PositionRepository;

@Service
public class PositionService {

	@Autowired
	JobmonitorConfigProperties config;

	@Autowired
	PositionRepository positionRepository;

	@Transactional
	public Position addNewPosition(Position position) {
		Position newPosition = positionRepository.save(position);
		newPosition.setUrl(config.getBaseUrl() + "/positions/" + newPosition.getId());
		return newPosition;
	}
	
	public Optional<Position> getPosition(long id) {
		return positionRepository.findById(id);
	}

}
