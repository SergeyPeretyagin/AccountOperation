package com.example.operations.repository;

import com.example.operations.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client save(Client client);


    Optional<Client> findById(UUID uuid);
    Optional<Client> findClientByAccount_NumberOfAccount(String numberOfAccount);
    boolean existsClientByAccount_NumberOfAccount(String numberAccount);
}
