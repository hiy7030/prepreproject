package com.prepreproject.member.service;

import com.prepreproject.exception.BusinessLogicException;
import com.prepreproject.exception.ExceptionCode;
import com.prepreproject.member.entity.Member;
import com.prepreproject.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 생성 -> member 객체를 받아야 -> 추후에 DB에 저장하는거니까!
    public Member createMember(Member member) {
        // 회원 email로 존재 여부 확인 후 리포지토리에 저장
    }
    // 회원 정보 수정
    public Member updateMember(Member member) {

        return member;
    }
    // 회원 조회
    public Member findMember(long memberId) {

        Member member = new Member("hgd@gmail.com", "홍길동", "010-1111-1111");
        member.setMemberId(memberId);
        throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
//        return member;
    }
    // 회원 목록 조회
    public Page<Member> findMembers(int page, int size) {

        Pageable pageable = PageRequest.of(page-1, size, Sort.by("memberId").descending()); // pageable은 0부터 시작하므로 -1 해줌
        Page<Member> memberPage = memberRepository.findAll(pageable);


        return memberPage;
    }
    // 회원 삭제
    public void deleteMember(long memberId) {
        //예외처리 구현을 위한 로직 삭제요망
        String logResult = null;
        System.out.println(logResult.toUpperCase());
    }

    // 검증 메서드
    // 1. 회원 존재 여부 확인 후 가입 -> 이메일로 조회 -> 예외 발생
    private void verifyExistsEmail(String email) {
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
