package com.prepreproject.member.service;

import com.prepreproject.member.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    // 회원 생성 -> member 객체를 받아야 -> 추후에 DB에 저장하는거니까!
    public Member createMember(Member member) {
        return null;
    }
    // 회원 정보 수정
    public Member updateMember(Member member) {
        return null;
    }
    // 회원 조회
    public Member findMember(long memberId) {
        return null;
    }
    // 회원 목록 조회
    public List<Member> findMembers(int page, int size) {
        return null;
    }
    // 회원 삭제
    public void deleteMember(long memberId) {

    }
}
