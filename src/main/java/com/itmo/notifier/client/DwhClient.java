package com.itmo.notifier.client;

import com.itmo.notifier.configuration.NotifierConfiguration;
import com.itmo.notifier.dto.BookingStatResponseDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "dwh", url = "http://localhost:8080/api/dwh",
             configuration = NotifierConfiguration.FeignClientConfig.class)
public interface DwhClient {
    @PostMapping()
    ResponseEntity<List<Long>> GetLastDayServices(@Valid @RequestBody List<BookingStatResponseDTO> bookings);
}
