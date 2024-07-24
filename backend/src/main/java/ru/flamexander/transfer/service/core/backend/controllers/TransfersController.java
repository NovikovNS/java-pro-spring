package ru.flamexander.transfer.service.core.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.flamexander.transfer.service.core.backend.services.TransferService;
import ru.flamexander.transfer.service.core.backend.services.TransferServiceImpl;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transfers")
public class TransfersController {
    private final TransferService transferService;

    @PostMapping("/execute")
    public void executeTransfer(
        @RequestParam Long sourceAccountId,
        @RequestParam Long targetAccountId,
        @RequestParam BigDecimal payment) {
        transferService.transfer(sourceAccountId, targetAccountId);
    }
}
