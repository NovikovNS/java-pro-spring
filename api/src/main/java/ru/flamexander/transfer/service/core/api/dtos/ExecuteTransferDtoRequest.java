package ru.flamexander.transfer.service.core.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Schema(description = "ДТО запроса выполнения перевода")
public class ExecuteTransferDtoRequest {

    private Long sourceClientId;

    private Long sourceAccountId;

    private Long targetClientId;

    private Long targetAccountId;

    private BigDecimal sum;
}
