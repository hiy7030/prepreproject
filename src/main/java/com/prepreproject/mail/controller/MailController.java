package com.prepreproject.mail.controller;

import com.prepreproject.mail.dto.MailDto;
import com.prepreproject.mail.service.MailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    // 메일 전송
    @PostMapping
    public void execMail(MailDto mailDto) {
        mailService.sendMail(mailDto);
    }
}
