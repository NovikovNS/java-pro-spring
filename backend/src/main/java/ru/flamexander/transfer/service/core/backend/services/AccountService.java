package ru.flamexander.transfer.service.core.backend.services;

import ru.flamexander.transfer.service.core.api.dtos.AccountDto;
import ru.flamexander.transfer.service.core.api.dtos.CreateAccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAllAccounts(Long clientId);
    AccountDto getAccountById(Long clientId, Long accountId);
    AccountDto createNewAccount(Long clientId, CreateAccountDto createAccountDto);
}
