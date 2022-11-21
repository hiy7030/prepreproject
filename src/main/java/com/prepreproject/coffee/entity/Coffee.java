package com.prepreproject.coffee.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Coffee {
    private Long coffeeId;
    private String korName;
    private String engName;
    private int price;
    private String coffeeCode;

    private enum coffeeStatus {
        COFFEE_FOR_SALE("판매중"),
        COFFEE_SOLD_OUT("판매중지");

        @Getter
        private String status;

        coffeeStatus(String status) {
            this.status = status;
        }
    }
}
