package com.prepreproject.order.dto;

import com.prepreproject.member.entity.Member;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
public class OrderPostDto {

    @Positive
    private long memberId;

    @Valid
    @NotNull
    private List<OrderCoffeeDto> orderCoffees;

    public Member getMember(){
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }
}
