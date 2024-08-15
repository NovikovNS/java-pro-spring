package ru.flamexander.transfer.service.core.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flamexander.transfer.service.core.api.dtos.AccountDto;
import ru.flamexander.transfer.service.core.api.dtos.ExecuteTransferDtoRequest;
import ru.flamexander.transfer.service.core.api.dtos.TransferDto;
import ru.flamexander.transfer.service.core.api.dtos.TransferStatus;
import ru.flamexander.transfer.service.core.backend.entities.Transfer;
import ru.flamexander.transfer.service.core.backend.repositories.TransferRepository;
import ru.flamexander.transfer.service.core.backend.validators.ExecuteTransferValidator;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final AccountServiceImpl accountsService;
    private final ExecuteTransferValidator executeTransferValidator;
    private final TransferRepository transferRepository;

    @Transactional
    public void transfer(ExecuteTransferDtoRequest request) {
        AccountDto sourceAccount = accountsService.getAccountById(request.getSourceClientId(), request.getSourceAccountId());
        AccountDto targetAccount = accountsService.getAccountById(request.getTargetClientId(), request.getTargetAccountId());
        executeTransferValidator.validate(request, sourceAccount, targetAccount);
        accountsService.updateBalance(sourceAccount.getId(), sourceAccount.getBalance().subtract(request.getSum()));
        accountsService.updateBalance(targetAccount.getId(), targetAccount.getBalance().add(request.getSum()));
        transferRepository.save(requestToEntity.apply(request,TransferStatus.COMPLETED));
    }

    @Override
    public List<TransferDto> allClientTransfers(Long clientId) {
        return transferRepository.findBySourceClientIdAndTargetClientId(clientId, clientId).stream().map(entityToDto).toList();
    }

    private Function<Transfer, TransferDto> entityToDto = transfer -> TransferDto.builder()
        .id(transfer.getId())
        .status(transfer.getStatus())
        .sum(transfer.getSum())
        .sourceClientId(transfer.getSourceClientId())
        .sourceAccountId(transfer.getSourceAccountId())
        .targetClientId(transfer.getTargetClientId())
        .targetAccountId(transfer.getTargetAccountId())
        .createDate(transfer.getCreateDate())
        .updateDate(transfer.getUpdateDate())
        .build();

    private BiFunction<ExecuteTransferDtoRequest, TransferStatus, Transfer> requestToEntity = (request, status) -> Transfer.builder()
        .sourceClientId(request.getSourceClientId())
        .sourceAccountId(request.getSourceAccountId())
        .targetClientId(request.getTargetClientId())
        .targetAccountId(request.getTargetAccountId())
        .sum(request.getSum())
        .status(status)
        .build();
}
