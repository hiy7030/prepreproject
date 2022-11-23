package com.prepreproject.member.service;

import com.prepreproject.member.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

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

        return member;
    }
    // 회원 목록 조회
    public List<Member> findMembers() {

        Member member1 = new Member("hgd@gmail.com", "홍길동", "010-1111-1111");
        member1.setMemberId(1L);
        Member member2 = new Member("aaa@gmail.com", "김길동", "010-2222-2222");
        member2.setMemberId(2L);

        return List.of(member1, member2);
    }
    // 회원 삭제
    public void deleteMember(long memberId) {

    }
}
