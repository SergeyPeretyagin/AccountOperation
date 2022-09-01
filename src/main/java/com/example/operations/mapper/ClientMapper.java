package com.example.operations.mapper;

import com.example.operations.DTO.ResponseClientDTO;
import com.example.operations.Entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mappings({
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName",defaultValue = "null"),
            @Mapping(target = "numberOfAccount", source = "client.account.numberOfAccount"),
            @Mapping(target = "amountOfAccount", source = "client.account.balance")})
    ResponseClientDTO clientToResponseClientDTO(Client client);
}
