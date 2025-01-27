package com.itmo.notifier.client;

import com.itmo.notifier.dto.BookingStatResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "services", url = "http://localhost:8080/api/booking/lastday")
public interface BookingClient {
    @GetMapping(path = "/lastday")
    ResponseEntity<List <BookingStatResponseDTO>> GetLastDayServices();
}
