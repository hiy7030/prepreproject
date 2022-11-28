package com.prepreproject.coffee.entity;

import com.prepreproject.audit.Audit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Coffee extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coffeeId;
    @Column(nullable = false, length = 50)
    private String korName;
    @Column(nullable = false, length = 50)
    private String engName;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false, length = 3)
    private String coffeeCode;
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private CoffeeStatus coffeeStatus = CoffeeStatus.COFFEE_FOR_SALE;

    public Coffee(String korName, String engName, int price, String coffeeCode, CoffeeStatus coffeeStatus) {
        this.korName = korName;
        this.engName = engName;
        this.price = price;
        this.coffeeCode = coffeeCode;
        this.coffeeStatus = coffeeStatus;
    }

    public enum CoffeeStatus {
        COFFEE_FOR_SALE("판매중"),
        COFFEE_SOLD_OUT("판매중지");

        @Getter
        private String status;

        CoffeeStatus(String status) {
            this.status = status;
        }
    }
}
