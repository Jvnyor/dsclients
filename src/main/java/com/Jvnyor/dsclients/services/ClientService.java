package com.Jvnyor.dsclients.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.Jvnyor.dsclients.dto.ClientDTO;
import com.Jvnyor.dsclients.entities.Client;
import com.Jvnyor.dsclients.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public Page<ClientDTO> findAll(PageRequest pageRequest) {
		return repository.findAll(pageRequest).map(x -> new ClientDTO(x));
	}
	
	public ClientDTO findById(Long id) {
		Client client = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found"));
		return new ClientDTO(client);
	}
	
	public ClientDTO save(ClientDTO dto) {
		Client client = repository.save(new Client(dto));
		return new ClientDTO(client);
	}
	
	public ClientDTO update(Long id, ClientDTO dto) {
		Client client = repository.getReferenceById(id);
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		client.setBirthDate(LocalDate.parse(dto.getBirthDate()));
		client.setChildren(dto.getChildren());
		return new ClientDTO(repository.save(client));
	}
	
	public void delete(Long id) {
		repository.delete(repository.getReferenceById(id));
	}
}
