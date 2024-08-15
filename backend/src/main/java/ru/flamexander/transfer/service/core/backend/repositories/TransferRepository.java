package ru.flamexander.transfer.service.core.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.flamexander.transfer.service.core.backend.entities.Transfer;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

    List<Transfer> findBySourceClientIdAndTargetClientId(Long sourceClientId, Long targetClientId);
}
