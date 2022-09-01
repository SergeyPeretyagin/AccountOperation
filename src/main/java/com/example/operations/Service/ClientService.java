package com.example.operations.Service;

import com.example.operations.Entity.Client;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    Client save(Client client);

    Optional<Client> findClient(String numberAccount);


    Client saveAll(Client fromTransfer, Client toTransfer);
}
