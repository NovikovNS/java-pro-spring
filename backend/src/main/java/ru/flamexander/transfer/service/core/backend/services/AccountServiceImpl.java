package ru.flamexander.transfer.service.core.backend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flamexander.transfer.service.core.api.dtos.AccountDto;
import ru.flamexander.transfer.service.core.api.dtos.CreateAccountDto;
import ru.flamexander.transfer.service.core.backend.entities.Account;
import ru.flamexander.transfer.service.core.backend.errors.AppLogicException;
import ru.flamexander.transfer.service.core.backend.errors.ResourceNotFoundException;
import ru.flamexander.transfer.service.core.backend.repositories.AccountsRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountsRepository accountsRepository;

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
        log.info("Account id = {} created from {}", account.getId(), createAccountDto);
        return entityToDto.apply(account);
    }

    @Transactional
    public void updateBalance(Long accountId, BigDecimal newBalance) {
        accountsRepository.updateBalance(newBalance, accountId);
    }

    private Function<Account, AccountDto> entityToDto = account -> AccountDto.builder()
        .id(account.getId())
        .accountNumber(account.getAccountNumber())
        .clientId(account.getClientId())
        .balance(account.getBalance())
        .build();
}
