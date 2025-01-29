package com.itmo.notifier.dto;

import java.time.LocalDateTime;

public record ErrorDTO(
        String message,   //сообщение об ошибке
        LocalDateTime timestamp    //время ошибки
) {
}

