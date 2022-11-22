package com.prepreproject.order.dto;

import com.prepreproject.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class OrderDto {

    @Getter
    @Setter
    public static class Post {
        private long orderId;
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
        private Order.OrderStatus orderStatus;
    }
}
