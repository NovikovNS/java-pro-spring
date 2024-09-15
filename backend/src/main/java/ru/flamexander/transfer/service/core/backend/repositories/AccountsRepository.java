package ru.flamexander.transfer.service.core.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.flamexander.transfer.service.core.backend.entities.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByClientId(Long clientId);
    Optional<Account> findByIdAndClientId(Long accountId, Long clientId);

    @Modifying
    @Query("update Account ac set ac.balance = :newBalance where ac.id = :accountId")
    void updateBalance(@Param("newBalance") BigDecimal newBalance,
        @Param("accountId") Long accountId);
}
