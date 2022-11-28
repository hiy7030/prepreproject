package com.prepreproject.member.entity;

import com.prepreproject.audit.Audit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 설정
    private Long memberId;
    @Column(nullable = false, updatable = false, unique = true)
    private String email;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 13, unique = true)
    private String phone;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    public Member(String email, String name, String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public enum MemberStatus {
        MEMBER_ACTIVE("활동중"),
        MEMBER_SLEEP("휴면"),
        MEMBER_QUIT("탈퇴");
        @Getter
        private String status;

        MemberStatus(String status) {
            this.status = status;
        }
    }
}
