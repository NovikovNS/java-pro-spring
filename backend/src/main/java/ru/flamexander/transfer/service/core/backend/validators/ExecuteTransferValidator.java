package ru.flamexander.transfer.service.core.backend.validators;

import org.springframework.stereotype.Component;
import ru.flamexander.transfer.service.core.api.dtos.AccountDto;
import ru.flamexander.transfer.service.core.api.dtos.ExecuteTransferDtoRequest;
import ru.flamexander.transfer.service.core.backend.errors.FieldValidationError;
import ru.flamexander.transfer.service.core.backend.errors.FieldsValidationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExecuteTransferValidator {

    public void validate(ExecuteTransferDtoRequest request, AccountDto sourceAccount, AccountDto targetAccount) {
        List<FieldValidationError> errorFields = new ArrayList<>();

        if (sourceAccount.getBalance().compareTo(request.getPayment()) < 0) {
            errorFields.add(new FieldValidationError("payment", "Нельзя перевести средств больше, чем есть на счете отправителя"));
        }

        if (request.getPayment().compareTo(BigDecimal.ZERO) < 0) {
            errorFields.add(new FieldValidationError("payment", "Нельзя перевести отрицательное количество средств"));
        }



        if (!errorFields.isEmpty()) {
            throw new FieldsValidationException(errorFields);
        }
    }

}
