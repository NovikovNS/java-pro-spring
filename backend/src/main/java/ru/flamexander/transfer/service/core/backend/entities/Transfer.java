package ru.flamexander.transfer.service.core.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.flamexander.transfer.service.core.api.dtos.TransferStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "transfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "transfer_status")
    @Enumerated(EnumType.STRING)
    private TransferStatus status;

    @Column(name = "source_client_id")
    private Long sourceClientId;

    @Column(name = "source_account_id")
    private Long sourceAccountId;

    @Column(name = "target_client_id")
    private Long targetClientId;

    @Column(name = "target_account_id")
    private Long targetAccountId;

    // TODO почему именно LocalDateTime?
    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name = "update_date")
    @UpdateTimestamp
    private LocalDateTime updateDate;

}
