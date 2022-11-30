package com.prepreproject.order.dto;

import com.prepreproject.order.entity.Order;
import com.prepreproject.order.entity.OrderCoffee;
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
    @Setter
    public static class Post {
        @NotBlank
        @Positive
        private long memberId;

        @Valid
        @NotNull
        private List<OrderCoffeeDto.Post> orderCoffees;
    }

    @Getter
    @Setter
    public static class Patch {
        private long orderId;
        private Order.OrderStatus orderStatus;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {

        private long orderId;
        private long memberId;
        private Order.OrderStatus orderStatus;
        private List<OrderCoffee> orderCoffees;
        private LocalDateTime createdAt;
    }
}
