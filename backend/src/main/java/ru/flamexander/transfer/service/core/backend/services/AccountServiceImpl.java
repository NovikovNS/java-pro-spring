package ru.flamexander.transfer.service.core.backend.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.flamexander.transfer.service.core.api.dtos.AccountDto;
import ru.flamexander.transfer.service.core.api.dtos.CreateAccountDto;
import ru.flamexander.transfer.service.core.backend.entities.Account;
import ru.flamexander.transfer.service.core.backend.errors.AppLogicException;
import ru.flamexander.transfer.service.core.backend.errors.ResourceNotFoundException;
import ru.flamexander.transfer.service.core.backend.repositories.AccountsRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountsRepository accountsRepository;

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class.getName());

    public List<AccountDto> getAllAccounts(Long clientId) {
        return accountsRepository.findAllByClientId(clientId).stream().map(entityToDto).collect(Collectors.toList());
    }

    public AccountDto getAccountById(Long clientId, Long accountId) {
        return accountsRepository.findByIdAndClientId(accountId, clientId)
            .map(entityToDto)
            .orElseThrow(() -> new ResourceNotFoundException(String.format("Не найден счет по идентификатору %s", accountId)));
    }

    public AccountDto createNewAccount(Long clientId, CreateAccountDto createAccountDto) {
        if (createAccountDto.getInitialBalance() == null) {
            throw new AppLogicException("VALIDATION_ERROR", "Создаваемый счет не может иметь null баланс");
        }
        Account account = new Account(clientId, createAccountDto.getInitialBalance());
        account = accountsRepository.save(account);
        logger.info("Account id = {} created from {}", account.getId(), createAccountDto);
        return entityToDto.apply(account);
    }

    private Function<Account, AccountDto> entityToDto = account -> AccountDto.builder()
        .id(account.getId())
        .accountNumber(account.getAccountNumber())
        .clientId(account.getClientId())
        .balance(account.getBalance())
        .build();
}
