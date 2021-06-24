package hu.mzsombor.jobmonitor.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.mzsombor.jobmonitor.dto.ClientDto;
import hu.mzsombor.jobmonitor.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	ClientService clientService;
	
	@PostMapping
	public String createClient(@RequestBody @Valid ClientDto clientDto) {
		return clientService.create(clientDto.getName(), clientDto.getEmail()).getUuid();
	}
}
