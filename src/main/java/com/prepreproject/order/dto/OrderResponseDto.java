package com.prepreproject.order.dto;

import com.prepreproject.member.entity.Member;
import com.prepreproject.order.entity.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private long orderId;

    @Setter(AccessLevel.NONE) // 생성을 막는 애트리뷰트
    private long memberId;
    private Order.OrderStatus orderStatus;
    private List<OrderCoffeeResponseDto> orderCoffees;
    private LocalDateTime createAt;

    public void setMember(Member member) {
        this.memberId = member.getMemberId();
    }
}
