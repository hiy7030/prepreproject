package com.prepreproject.coffee.entity;

import com.prepreproject.audit.Audit;
import com.prepreproject.order.entity.OrderCoffee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column(nullable = false, length = 3, unique = true)
    private String coffeeCode;
    @Column(nullable = false, length = 20)
    @Enumerated(value = EnumType.STRING)
    private CoffeeStatus coffeeStatus = CoffeeStatus.COFFEE_FOR_SALE;

    public Coffee(Long coffeeId) {
        this.coffeeId = coffeeId;
    }

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

    // OrderCoffee와 매핑
    @OneToMany(mappedBy = "coffee")
    private List<OrderCoffee> orderCoffees = new ArrayList<>();

    public void setOrderCoffee(OrderCoffee orderCoffee){
        orderCoffees.add(orderCoffee);
        if(orderCoffee.getCoffee() != this) {
            orderCoffee.setCoffee(this);
        }
    }

}
