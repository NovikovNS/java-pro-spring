package ru.flamexander.transfer.service.core.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "Перевод")
public class TransferDto {

    @Schema(description = "Внутренний ID перевода")
    private Long id;

    @Schema(description = "Сумма перевода")
    private BigDecimal payment;

    @Schema(description = "ID клиента счёта списания")
    private Long sourceClientId;

    @Schema(description = "Счёт списания")
    private Long sourceAccountId;

    @Schema(description = "ID клиента счёта зачисления")
    private Long targetClientId;

    @Schema(description = "Счёт зачисления")
    private Long targetAccountId;

    @Schema(description = "Статус перевода")
    private TransferStatus transferStatus;

    @Schema(description = "Дата создания")
    private LocalDateTime createDate;

    @Schema(description = "Дата последнего изменения")
    private LocalDateTime updateDate;
}
