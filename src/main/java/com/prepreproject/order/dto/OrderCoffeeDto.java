package com.prepreproject.order.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Positive;
@Getter
public class OrderCoffeeDto {
        @Positive
        private long coffeeId;

        @Positive
        private Integer quantity;
}
