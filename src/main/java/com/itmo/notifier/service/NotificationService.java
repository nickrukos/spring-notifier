package com.itmo.notifier.service;

import com.itmo.notifier.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final JavaMailSender javaMailSender;
    //@Value("${app.from}")
    private final String appFrom = "ООО Биг";
    private SimpleMailMessage getSimpleMessage(String message, String subject, String[] setTo){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(appFrom);
        mailMessage.setTo(setTo); // кому
        mailMessage.setSubject(subject); // тема письма
        mailMessage.setText(message); // текст письма
        return mailMessage;
    }
    public void notifyCancel(UserRequestDto user){
        String[] mail = {user.userMail()};
        StringBuilder message = new StringBuilder();
        message.append("Уважаемый(ая) ").append(user.userName()).append("!").append(System.lineSeparator())
                .append("Ваша бронь отменена по техническим причинам!").append(System.lineSeparator())
                .append("Ваша скидка на последующие заказы увеличена и составила ")
                .append(user.discount()).append(" процентов").append(System.lineSeparator())
                .append("Техническая поддержка ООО \"Биг-Бен\"");
        javaMailSender.send(getSimpleMessage(message.toString(),"Уведомление",mail));
    }
    public void notifyEdit(UserRequestDto user, String date){
        String[] mail = {user.userMail()};
        StringBuilder message = new StringBuilder();
        message.append("Уважаемый(ая) ").append(user.userName()).append("!").append(System.lineSeparator())
                .append("Ваша бронь перенесена на ").append(System.lineSeparator())
                .append(date).append(System.lineSeparator())
                .append("Техническая поддержка ООО \"Биг-Бен\"");
        javaMailSender.send(getSimpleMessage(message.toString(),"Уведомление",mail));
    }
}
