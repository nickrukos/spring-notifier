package com.itmo.notifier.controller;
import com.itmo.notifier.dto.UserRequestDto;
import com.itmo.notifier.service.NotificationSchedule;
import com.itmo.notifier.service.NotificationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/notifier")
public class NotificationController {
    private final NotificationService notificationService;
    private final NotificationSchedule notificationSchedule;
    @PostMapping(path = "/cancel", produces = "application/json")
    public ResponseEntity<Void> notifyCancel(@Valid @RequestBody UserRequestDto user){
        notificationService.notifyCancel(user);
        return ResponseEntity.ok().build();
    }
    @PostMapping(path = "/edit", produces = "application/json")
    public ResponseEntity<Void> notifyEdit(@Valid @RequestBody UserRequestDto user,
                                           @NotNull @RequestParam String date){
        notificationService.notifyEdit(user,date);
        return ResponseEntity.ok().build();
    }
    //получение статистической информации по запросу администратора
    @PostMapping(path = "/stat_info")
    public ResponseEntity<?> stat_start() throws ExecutionException, InterruptedException {
        CompletableFuture<ResponseEntity<?>> future = notificationSchedule.SendToDwh();
        return future.get();
    }
}
