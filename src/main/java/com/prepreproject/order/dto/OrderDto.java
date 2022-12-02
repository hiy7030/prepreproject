package com.prepreproject.order.dto;

import com.prepreproject.member.entity.Member;
import com.prepreproject.order.entity.Order;
import com.prepreproject.order.entity.OrderCoffee;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;
@Getter
public class OrderDto {

    @Getter
    public static class Post {

        @Positive
        private long memberId;

        @Valid
        @NotNull
        private List<OrderCoffeeDto.Post> orderCoffees;

        public Member getMember(){
            Member member = new Member();
            member.setMemberId(memberId);
            return member;
        }
    }

    @Getter
    @Setter
    public static class Patch {
        private long orderId;
        private Order.OrderStatus orderStatus;
    }

    @Getter
    @Setter
    public static class Response {

        private long orderId;

        @Setter(AccessLevel.NONE) // 생성을 막는 애트리뷰트
        private long memberId;
        private Order.OrderStatus orderStatus;
        private List<OrderCoffeeDto.Response> orderCoffees;
        private LocalDateTime createdAt;

        public void setMember(Member member) {
            this.memberId = member.getMemberId();
        }
    }
}
