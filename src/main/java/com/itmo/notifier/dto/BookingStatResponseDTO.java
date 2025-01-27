package com.itmo.notifier.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record BookingStatResponseDTO(
        @NotNull
        @Min(1)
        long id,  //идентификатор брони
        @NotNull
        @Min(1)
        long serviceId,  //идентификатор услуги
        @NotNull
        @Min(1)
        long userId,  //идентификатор пользователя
        @NotNull
        @Min(1)
        long value   //стоимость брони с учетом скидки
)
{
}
