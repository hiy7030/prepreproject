package com.prepreproject.advice.stub;

import com.prepreproject.member.dto.MemberDto;
import com.prepreproject.member.entity.Member;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect //Jpa 라이브러리 추가 시 AOP 사용 가능
//@Component
public class MemberStubAdvice {
    @Pointcut("execution(* com.prepreproject.member.controller.memberController.postMember(..))")
    public void postMemberMethod() {}

    @Around(value = "postMemberMethod()")
    public ResponseEntity aroundPostMember(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("### Apply Around Join Point");
        MemberDto.Response response = (MemberDto.Response) joinPoint.proceed();
        response = new MemberDto.Response(1l, "hgd@gmail.com",
                "홍길동", "010-1111-1111", Member.MemberStatus.MEMBER_ACTIVE );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
