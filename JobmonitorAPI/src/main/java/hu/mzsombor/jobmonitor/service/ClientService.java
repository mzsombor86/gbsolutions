package hu.mzsombor.jobmonitor.service;

import java.util.UUID;

import javax.persistence.NonUniqueResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.mzsombor.jobmonitor.model.Client;
import hu.mzsombor.jobmonitor.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Transactional
	public Client create(String name, String email) {
		if (!clientRepository.findByEmail(email).isEmpty())
			throw new NonUniqueResultException(email + " email address is in use!");
		Client client = new Client(name, email, UUID.randomUUID().toString());
		return clientRepository.save(client);
	}

}
