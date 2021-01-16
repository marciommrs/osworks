package com.green.osworks.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.osworks.domain.exception.DomainException;
import com.green.osworks.domain.model.Client;
import com.green.osworks.domain.repository.ClientRepository;

@Service
public class RegisterClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client save(Client client) {
		Client clientExist = clientRepository.findByEmail(client.getEmail());
		
		if (clientExist != null && !clientExist.equals(client)) {
			throw new DomainException("Já existe um cliente cadastrado com este e-mail.");
		}
		
		
		return clientRepository.save(client);
	}
	
	public void deleteById(Long clientId) {
		clientRepository.deleteById(clientId);
	}

}
