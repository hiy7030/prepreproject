package com.prepreproject.order.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Positive;

public class OrderCoffeeDto {

    @Getter
    public static class Post {

        @Positive
        private Long coffeeId;

        @Positive
        private int quantity;
    }

    @Getter
    @Builder
    public static class Response {
        private long coffeeId;
        private int quantity;
        private String korName;
        private String engName;
        private int price;
    }
}
