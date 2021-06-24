package hu.mzsombor.jobmonitor.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.mzsombor.jobmonitor.dto.PositionPushDto;
import hu.mzsombor.jobmonitor.dto.PositionResultDto;
import hu.mzsombor.jobmonitor.mapper.PositionMapper;
import hu.mzsombor.jobmonitor.service.ClientService;
import hu.mzsombor.jobmonitor.service.PositionService;

@RestController
@RequestMapping("/positions")
@Validated
public class PositionController {

	@Autowired
	PositionService positionService;

	@Autowired
	ClientService clientService;

	@Autowired
	PositionMapper positionMapper;

	@PostMapping
	public String addPosition(@RequestBody @Valid PositionPushDto positionDto, @RequestParam String apiKey) {
		if (!clientService.isUuidExists(apiKey))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		return positionService.addNewPosition(positionMapper.positionPushDtoToPosition(positionDto)).getUrl();
	}

	@GetMapping("/{id}")
	public PositionResultDto getPosition(@PathVariable long id, @RequestParam String apiKey) {
		if (!clientService.isUuidExists(apiKey))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		return positionMapper.positionToPositionResultDto(
				positionService.getPosition(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
	}
}
