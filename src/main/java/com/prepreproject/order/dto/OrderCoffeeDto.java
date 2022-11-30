package com.prepreproject.order.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class OrderCoffeeDto {

    public static class Post {
        @Positive
        private long coffeeId;

        @NotNull
        @Positive
        private int quantity;
    }

    public static class Response {
        private long coffeeId;
        private int quantity;
        private String korName;
        private String engName;
        private int price;
    }
}
