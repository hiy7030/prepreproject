package com.prepreproject.member.service;

import com.prepreproject.event.MemberEvent;
import com.prepreproject.exception.BusinessLogicException;
import com.prepreproject.exception.ExceptionCode;
import com.prepreproject.mail.service.MailService;
import com.prepreproject.member.entity.Member;
import com.prepreproject.member.repository.MemberRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final MailService mailService;

    public MemberService(MemberRepository memberRepository,
                         ApplicationEventPublisher applicationEventPublisher,
                         MailService mailService) {
        this.memberRepository = memberRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.mailService = mailService;
    }

    // 회원 생성 -> member 객체를 받아야 -> 추후에 DB에 저장하는거니까!
    public Member createMember(Member member) {
        // 회원 email로 존재 여부 확인 후 리포지토리에 저장
        verifyExistsEmail(member.getEmail());
        Member saveMember = memberRepository.save(member);
        // 메일 전송 이벤트 발생! -> 이벤트 리스너가 해당 이벤트를 받아 로직을 실행! (이벤트 리스너 -> 이벤트 핸들러)
        applicationEventPublisher.publishEvent(new MemberEvent(saveMember));

        return saveMember;

    }
    // 회원 정보 수정
    @Transactional(propagation = Propagation.REQUIRED)
    public Member updateMember(Member member) {
        // 회원을 찾아서 입력 받은 값으로 회원 정보 수정
        Member findMember = findVerifiedMember(member.getMemberId());
        // 변경 가능한 필드 -> 이름, 폰번호, 회원 상태
        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));

        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> findMember.setPhone(phone));

        Optional.ofNullable(member.getMemberStatus())
                .ifPresent(status -> findMember.setMemberStatus(status));

        return  memberRepository.save(findMember);
    }
    // 회원 조회
    public Member findMember(long memberId) {

        return findVerifiedMember(memberId);

    }
    // 회원 목록 조회
    public Page<Member> findMembers(int page, int size) {

        Pageable pageable = PageRequest.of(page-1, size, Sort.by("memberId").descending()); // pageable은 0부터 시작하므로 -1 해줌
        Page<Member> memberPage = memberRepository.findAll(pageable);


        return memberPage;
    }
    // 회원 삭제
    public void deleteMember(long memberId) {
        // 회원 찾아서 삭제
        Member findMember = findVerifiedMember(memberId);
        memberRepository.delete(findMember);
    }

    // 검증 메서드
    // 1. 회원 존재 여부 확인 후 가입 -> 이메일로 조회 -> 예외 발생
    private void verifyExistsEmail(String email) { // 이메일로 조회하는 이유 : 고유값이기 때문에
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if(optionalMember.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }
    }
    // 2. 회원 존재 여부 확인 -> 멤버 아이디로 조회 -> 찾은 회원 반환
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member findMember = optionalMember.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }
}
