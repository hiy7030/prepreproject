package com.prepreproject.order.entity;

import com.prepreproject.audit.Audit;
import com.prepreproject.coffee.entity.Coffee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderCoffee extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderCoffeeID;

    @Column(nullable = false)
    private int quantity;

    // Order와 매핑 -> Ordercoffee(N) : order(1)
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    public void setOrder(Order order) {
        this.order = order;
        if(!this.order.getOrderCoffees().contains(this)){
            this.order.getOrderCoffees().add(this);
        }
    }

    // Coffee와 매핑 -> OrderCoffee(N) : coffee(1)
    @ManyToOne
    @JoinColumn(name = "COFFEE_ID")
    private Coffee coffee;

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
        if(!this.coffee.getOrderCoffees().contains(this)) {
            this.coffee.getOrderCoffees().add(this);
        }
    }
}
