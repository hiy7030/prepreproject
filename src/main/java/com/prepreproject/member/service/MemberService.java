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

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 생성 -> member 객체를 받아야 -> 추후에 DB에 저장하는거니까!
    public Member createMember(Member member) {
        member.setMemberId(1L);

        return member;
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
}
