package com.example.operations.mapper;

import com.example.operations.DTO.RequestClientDto;
import com.example.operations.Entity.Client;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public Client requestClientDtoToClient(RequestClientDto requestClientDto){
        return Client.builder()
                .firstName(requestClientDto.getFirstName())
                .lastName(requestClientDto.getLastName())
                .build();
    }
}
