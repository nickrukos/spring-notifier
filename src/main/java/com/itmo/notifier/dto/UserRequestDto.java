package com.itmo.notifier.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserRequestDto(
        @NotNull
        long id,
        @NotNull
        String userName,
        @NotNull
        String userMail,
        @NotNull
        int discount
) {

}
