package com.prepreproject.mail.service;

import com.prepreproject.mail.dto.MailDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mailSender;

    @Value("${GMAIL_ADDRESS}")
    private static String FROM_ADDRESS;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getEmail());
        message.setFrom(FROM_ADDRESS);
        message.setSubject(String.format("%S 님, 회원 가입이 완료되었습니다.", mailDto.getName()));
        message.setText(String.format("%s님, %s의 정보로 가입을 완료하였습니다.", mailDto.getName(), mailDto.getPhone()));

        mailSender.send(message);
    }
}
