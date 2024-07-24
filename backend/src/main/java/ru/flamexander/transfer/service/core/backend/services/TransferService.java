package ru.flamexander.transfer.service.core.backend.services;

public interface TransferService {
    void transfer(Long sourceAccountId, Long targetAccountId);
}
