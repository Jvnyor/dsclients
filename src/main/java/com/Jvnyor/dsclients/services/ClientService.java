package com.Jvnyor.dsclients.services;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.Jvnyor.dsclients.dto.ClientRequestDTO;
import com.Jvnyor.dsclients.dto.ClientResponseDTO;
import com.Jvnyor.dsclients.entities.Client;
import com.Jvnyor.dsclients.repositories.ClientRepository;
import com.Jvnyor.dsclients.services.exceptions.CpfExistingException;
import com.Jvnyor.dsclients.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public Page<ClientResponseDTO> findAll(PageRequest pageRequest) {
		return repository.findAll(pageRequest).map(x -> convertToDTO(x));
	}
	
	public ClientResponseDTO findById(Long id) {
		Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found"));
		return convertToDTO(client);
	}

	public ClientResponseDTO save(ClientRequestDTO dto) {
		if (findByCpf(dto.getCpf()) != null) {
			throw new CpfExistingException("CPF already exists");
		}
		return convertToDTO(repository.save(convertToEntityToSave(dto)));
	}
	
	public ClientResponseDTO update(Long id, ClientRequestDTO dto) {
		Client client = repository.getReferenceById(id);
		if (Objects.isNull(client)) {
			throw new ResourceNotFoundException("Id not found");
		} else if (findByCpf(dto.getCpf()) != null && !findByCpf(dto.getCpf()).getId().equals(client.getId())) {
			throw new CpfExistingException("CPF already exists");
		}
		return convertToDTO(repository.save(convertToEntityToUpdate(client, dto)));
	}
	
	public void delete(Long id) {
		Client client = repository.getReferenceById(id);
		repository.delete(client);
	}
	
	private ClientResponseDTO convertToDTO(Client entity) {
		return ClientResponseDTO.builder()
					.id(entity.getId())
					.cpf(entity.getCpf())
					.name(entity.getName())
					.income(entity.getIncome())
					.birthDate(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(entity.getBirthDate()))
					.children(entity.getChildren())
					.build();
	}
	
	private Client convertToEntityToSave(ClientRequestDTO dto) {
		return Client.builder()
				.cpf(dto.getCpf())
				.name(dto.getName())
				.income(dto.getIncome())
				.birthDate(dto.getBirthDate())
				.children(dto.getChildren())
				.build();
	}
	
	private Client convertToEntityToUpdate(Client client, ClientRequestDTO dto) {
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
		return client;
	}
	
	public ClientResponseDTO findByCpfReturnsDTO(String cpf) {
		Client client = repository.findByCpf(cpf);
		if (Objects.isNull(client)) {
			throw new ResourceNotFoundException("CPF not found");
		}
		return convertToDTO(client);
	}
	
	private Client findByCpf(String cpf) {
		return repository.findByCpf(cpf);
	}
}
