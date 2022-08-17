package com.Jvnyor.dsclients.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Jvnyor.dsclients.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	Client findByCpf(String cpf);
}
