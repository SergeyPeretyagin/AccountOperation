package com.example.operations.Service;

import com.example.operations.DTO.RequestTransferDto;
import com.example.operations.Entity.Client;

import java.math.BigDecimal;


public interface OperationService {
    public Client createAccount(Client client);

    Client upBalance(String numberAccount, BigDecimal amount);

    Client downBalance(String numberAccount, BigDecimal amount);
    Client makeTransfer(RequestTransferDto requestTransferDto);
}
