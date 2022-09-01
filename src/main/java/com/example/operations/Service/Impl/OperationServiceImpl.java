package com.example.operations.Service.Impl;

import com.example.operations.DTO.RequestTransferDto;
import com.example.operations.Entity.Account;
import com.example.operations.Entity.Client;
import com.example.operations.Exception.BadRequestException;
import com.example.operations.Service.ClientService;
import com.example.operations.Service.OperationService;
import com.example.operations.util.HelperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@Slf4j
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final ClientService clientService;


    public Client createAccount(Client client){
        setUpClient(client);
        return clientService.save(client);
    }


    public Client upBalance(String numberAccount, BigDecimal amount) {
        Client client =  clientService.findClient(numberAccount)
                .map(client1 -> {
                    Account account = client1.getAccount();
                    account.setBalance(account.getBalance().add(amount));
                    client1.setAccount(account);
                    return client1;
                })
                .orElseThrow(()->{
                    log.error("Client not found {}",numberAccount);
                    throw new BadRequestException("Client not found");
                });
        return clientService.save(client);

    }

    @Override
    public Client downBalance(String numberAccount, BigDecimal amount) {
        Client client =  clientService.findClient(numberAccount)
                .map(existClient -> {
                    if (existClient.getAccount().getBalance().compareTo(amount)<0)
                        throw new BadRequestException("The balance does not allow you to perform the operation");
                    Account account = existClient.getAccount();
                    account.setBalance(account.getBalance().subtract(amount));
                    existClient.setAccount(account);
                    return existClient;
                })
                .orElseThrow(()->{
                    log.error("Client not found {}",numberAccount);
                    throw new BadRequestException("Client not found");
                });
        return clientService.save(client);
    }

    public Client makeTransfer(RequestTransferDto requestTransferDto){
        Client fromTransfer = clientService.findClient(requestTransferDto.getFromNumberAccount())
                .map(client -> {
                    if (client.getAccount().getBalance().compareTo(requestTransferDto.getTransferAmount())<0)
                        throw new BadRequestException("The balance does not allow you to perform the operation");
                    Account account = client.getAccount();
                    account.setBalance(account.getBalance().subtract(requestTransferDto.getTransferAmount()));
                    client.setAccount(account);
                    return client;
                })
                .orElseThrow(()->{
                    log.error("Client not found {}",requestTransferDto.getFromNumberAccount());
                    throw new BadRequestException("Client not found");
                });
        Client toTransfer = clientService.findClient(requestTransferDto.getToNumberAccount())
                .map(client -> {
                    Account account = client.getAccount();
                    account.setBalance(account.getBalance().add(requestTransferDto.getTransferAmount()));
                    client.setAccount(account);
                    return client;
                })
                .orElseThrow(()->{
                    log.error("Client not found {}",requestTransferDto.getToNumberAccount());
                    throw new BadRequestException("Client not found");
                });
        return clientService.saveAll(fromTransfer,toTransfer);

    }


    private void setUpClient(Client client){
        Account account = Account.builder()
                .numberOfAccount(HelperUtil.createNumberAccount())
                .balance(BigDecimal.valueOf(0.0))
                .build();
        client.setAccount(account);

    }
}
