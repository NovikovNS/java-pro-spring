package ru.flamexander.transfer.service.core.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.flamexander.transfer.service.core.api.dtos.ExecuteTransferDtoRequest;
import ru.flamexander.transfer.service.core.api.dtos.TransferDto;
import ru.flamexander.transfer.service.core.backend.services.TransferService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transfers")
public class TransfersController {
    private final TransferService transferService;

    @PostMapping("/execute")
    public void executeTransfer(@RequestBody ExecuteTransferDtoRequest request) {
        transferService.transfer(request);
    }

    @GetMapping()
    public ResponseEntity<List<TransferDto>> getTransfersByClientId(@RequestParam Long sourceClientId){
        return ResponseEntity.ok(transferService.transfersFromClientId(sourceClientId));
    }
}
