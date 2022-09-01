package com.example.operations.Service.Impl;

import com.example.operations.Entity.Client;
import com.example.operations.Service.ClientService;
import com.example.operations.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;


    @Override
    @Transactional
    public Client saveAll(Client fromTransfer, Client toTransfer) {
        clientRepository.save(toTransfer);
        return clientRepository.save(fromTransfer);
    }

    @Override
    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }


    @Override
    @Transactional
    public Optional<Client> findClient(String numberAccount) {
        return clientRepository.findClientByAccount_NumberOfAccount(numberAccount);
    }

}
