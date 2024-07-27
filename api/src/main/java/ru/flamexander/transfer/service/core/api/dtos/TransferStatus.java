package ru.flamexander.transfer.service.core.api.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TransferStatus {
    CREATED("Создан"),
    IN_PROGRESS("В обработке"),
    COMPLETED("ВЫполнен"),
    ERROR("Ошибка");

    private final String name;
}
