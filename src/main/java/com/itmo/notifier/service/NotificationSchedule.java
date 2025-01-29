package com.itmo.notifier.service;

import com.itmo.notifier.client.BookingClient;
import com.itmo.notifier.dto.BookingStatResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
@EnableScheduling
public class NotificationSchedule {
    private final BookingClient bookingClient;
    //@Scheduled(fixedRate = 1, timeUnit = TimeUnit.DAYS)
    public void SendToDwh(){
        //получить перечень бронирований за прошедший день
        List<BookingStatResponseDTO> bookings = bookingClient.GetLastDayServices().getBody();

    }
}
