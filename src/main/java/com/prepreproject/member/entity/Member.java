package com.prepreproject.member.entity;

import com.prepreproject.audit.Audit;
import com.prepreproject.order.entity.Order;
import com.prepreproject.stamp.Stamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Enumerated(value = EnumType.STRING)
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

    // 엔티티 매핑
    // 1. order(N) -> 객체 여러개
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    // 매핑된 필드에 대한 setter 필요!
    public void setOrder(Order order) {
        orders.add(order);
        if(order.getMember() != this) {
            order.setMember(this);
        }
    }

    // 2. stamp(1)
    @OneToOne(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Stamp stamp;

    public void setStamp(Stamp stamp) {
        this.stamp = stamp;
        if(stamp.getMember() != this) {
            stamp.setMember(this);
        }
    }
}
