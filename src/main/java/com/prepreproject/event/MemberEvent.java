package com.prepreproject.event;

import com.prepreproject.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberEvent { // 이벤트 퍼블리셔
    private Member member;

    public MemberEvent(Member member) {
        this.member = member;
    }
}
