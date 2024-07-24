package ru.flamexander.transfer.service.core.backend.services;

import ru.flamexander.transfer.service.core.api.dtos.AccountDto;
import ru.flamexander.transfer.service.core.api.dtos.CreateAccountDto;
import ru.flamexander.transfer.service.core.backend.entities.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<AccountDto> getAllAccounts(Long clientId);
    AccountDto getAccountById(Long clientId, Long accountId);
    AccountDto createNewAccount(Long clientId, CreateAccountDto createAccountDto);
}
