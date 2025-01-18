package com.itmo.notifier.controller;
import com.itmo.notifier.dto.UserRequestDto;
import com.itmo.notifier.service.NotificationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notifier")
public class NotificationController {
    private final NotificationService notificationService;
    @PostMapping("/cancel")
    public ResponseEntity<Void> notifyCancel(@Valid @RequestBody UserRequestDto user,
                                             HttpServletRequest request){
        notificationService.notifyCancel(user);
        return ResponseEntity.ok().build();
    }

}
