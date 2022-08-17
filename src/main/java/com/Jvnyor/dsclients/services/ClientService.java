package com.Jvnyor.dsclients.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.Jvnyor.dsclients.dto.ClientRequestDTO;
import com.Jvnyor.dsclients.dto.ClientResponseDTO;
import com.Jvnyor.dsclients.entities.Client;
import com.Jvnyor.dsclients.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public Page<ClientResponseDTO> findAll(PageRequest pageRequest) {
		return repository.findAll(pageRequest).map(x -> new ClientResponseDTO(x));
	}
	
	public ClientResponseDTO findById(Long id) {
		Client client = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found"));
		return new ClientResponseDTO(client);
	}
	
	public ClientResponseDTO save(ClientRequestDTO dto) {
		if (repository.findByCpf(dto.getCpf()) != null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CPF already exists");
		}
		Client client = repository.save(new Client(dto));
		return new ClientResponseDTO(client);
	}
	
	public ClientResponseDTO update(Long id, ClientRequestDTO dto) {
		Client client = repository.getReferenceById(id);
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
		return new ClientResponseDTO(repository.save(client));
	}
	
	public void delete(Long id) {
		repository.delete(repository.getReferenceById(id));
	}
}
