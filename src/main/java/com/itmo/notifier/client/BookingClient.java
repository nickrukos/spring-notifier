package com.itmo.notifier.client;

import org.springframework.cloud.openfeign.FeignClient;
@FeignClient(value = "services", url = "http://localhost:8080/api/booking/lastday")
public class BookingClient {


}
