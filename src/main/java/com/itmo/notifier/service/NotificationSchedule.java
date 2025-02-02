package com.itmo.notifier.service;

import com.itmo.notifier.client.BookingClient;
import com.itmo.notifier.client.DwhClient;
import com.itmo.notifier.dto.BookingStatResponseDTO;
import feign.Feign;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
@EnableScheduling
public class NotificationSchedule {
    private final BookingClient bookingClient;
    private final DwhClient dwhClient;

    @Async("notifier-executor")
    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.DAYS)
    public CompletableFuture<ResponseEntity<?>>  SendToDwh(){
        //получить перечень бронирований за прошедший день
        List<BookingStatResponseDTO> bookings = bookingClient.GetLastDayServices().getBody();
        URI location = URI.create("http://localhost:8080/admin_cab");
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.setLocation(location);
        responseHeader.set("Информация для Администратора", "Сообщение от сервиса статистики");
        if(bookings.isEmpty()){
            //отправлять нечего - список бронирований пуст
            //отправляем сообщение Администратору
            ResponseEntity <String> resp = new ResponseEntity<>(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
                                                                + " Отсутствуют бронирования",
                                                                responseHeader,
                                                                HttpStatus.NO_CONTENT);
            return CompletableFuture.completedFuture(resp);
        }
        List <Long> listId = dwhClient.GetLastDayServices(bookings).getBody();
        if(listId.size() != bookings.size()){
            //передался не весь перечень броней
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < listId.size(); i++) {
                stringBuilder.append(listId.get(0)).append(System.lineSeparator());
            }
            ResponseEntity <String> resp
                    = new ResponseEntity<>(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
                                                 + " Переданы только следующие брони:"
                                                 + System.lineSeparator().toString()
                                                 + stringBuilder,
                                                 responseHeader,
                                                 HttpStatus.PARTIAL_CONTENT);
            return CompletableFuture.completedFuture(resp);
        }
        //передана вся информация
        ResponseEntity <String> resp = new ResponseEntity<>(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
                + " Передана вся требуемая информация",
                responseHeader,
                HttpStatus.OK);
        return CompletableFuture.completedFuture(resp);
    }
}
