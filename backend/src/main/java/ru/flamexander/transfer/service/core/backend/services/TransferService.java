package ru.flamexander.transfer.service.core.backend.services;

import ru.flamexander.transfer.service.core.api.dtos.ExecuteTransferDtoRequest;
import ru.flamexander.transfer.service.core.api.dtos.TransferDto;

import java.util.List;

public interface TransferService {
    void transfer(ExecuteTransferDtoRequest request);
    List<TransferDto> allClientTransfers(Long clientId);
}
