package com.prepreproject.event;

import com.prepreproject.exception.BusinessLogicException;
import com.prepreproject.exception.ExceptionCode;
import com.prepreproject.mail.dto.MailDto;
import com.prepreproject.mail.service.MailService;
import com.prepreproject.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
@Slf4j
@EnableAsync
public class EventHandler { // 이벤트가 발생하면 실행할 로직 구현!

    // 멤버 서비스, 메일 전송을 하는 서비스계층 di
    private final MemberService memberService;
    private final MailService mailService;

    public EventHandler(MemberService memberService, MailService mailService) {
        this.memberService = memberService;
        this.mailService = mailService;
    }
    @EventListener
    @Async // 비동기
    public void handleEmailSending(MemberEvent memberEvent) {
        // 이메일 전송
        // 예외 발생 시 처리할 로직 구현 -> db에서 회원 삭제
        try{
            log.info("메일 전송");

            MailDto mailDto = new MailDto();
            mailDto.setEmail(memberEvent.getMember().getEmail());
            mailDto.setName(memberEvent.getMember().getName());
            mailService.sendMail(mailDto);

        } catch (Exception e) {
            log.error("MailSendException happened: ", e);

            memberService.deleteMember(memberEvent.getMember().getMemberId());
            throw new BusinessLogicException(ExceptionCode.FAILED_TO_JOIN);
        }

    }
}
