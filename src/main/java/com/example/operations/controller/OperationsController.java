package com.example.operations.controller;

import com.example.operations.DTO.RequestBalanceDTO;
import com.example.operations.DTO.RequestClientDto;
import com.example.operations.DTO.RequestTransferDto;
import com.example.operations.DTO.ResponseClientDTO;
import com.example.operations.Entity.Client;
import com.example.operations.Service.OperationService;
import com.example.operations.mapper.AccountMapper;
import com.example.operations.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/operation")
@RestController
@RequiredArgsConstructor
@Slf4j
public class OperationsController {
    private final OperationService operationService;
    private final AccountMapper accountMapper;
    private final ClientMapper mapper;

    @PostMapping("/client")
    public ResponseEntity<ResponseClientDTO> createAccount(@Valid @RequestBody RequestClientDto requestClientDto){
        log.info("requestClientDto {} ",requestClientDto);
        Client client = operationService.createAccount(accountMapper.requestClientDtoToClient(requestClientDto));
        ResponseClientDTO responseClientDTO = mapper.clientToResponseClientDTO(client);
        return ResponseEntity.ok().body(responseClientDTO);
    }

    @PatchMapping("/adding")
    public ResponseEntity<ResponseClientDTO> addBalance(@Valid @RequestBody RequestBalanceDTO requestBalanceDTO){
        log.info("requestBalanceDTO {}",requestBalanceDTO);
        ResponseClientDTO responseClientDTO = mapper.clientToResponseClientDTO(
                operationService.upBalance(requestBalanceDTO.getNumberAccount(),requestBalanceDTO.getAmount()));
        return ResponseEntity.ok().body(responseClientDTO);
    }

    @PatchMapping("/taking")
    public ResponseEntity<ResponseClientDTO> takeBalance(@Valid @RequestBody RequestBalanceDTO requestBalanceDTO){
        log.info("requestBalanceDTO {}",requestBalanceDTO);
        ResponseClientDTO responseClientDTO = mapper.clientToResponseClientDTO(
                operationService.downBalance(requestBalanceDTO.getNumberAccount(),requestBalanceDTO.getAmount()));
        return ResponseEntity.ok().body(responseClientDTO);
    }



    @PatchMapping("/transfer")
    public ResponseEntity<ResponseClientDTO> moneyTransfer(@Valid @RequestBody RequestTransferDto requestTransferDto){
        log.info("requestTransferDto {} ",requestTransferDto);
        Client client = operationService.makeTransfer(requestTransferDto);
        ResponseClientDTO responseClientDTO = mapper.clientToResponseClientDTO(client);
        log.info("responseClientDTO {}",responseClientDTO);
        return ResponseEntity.ok().body(responseClientDTO);
    }
}
