package com.prepreproject.order.entity;

import com.prepreproject.audit.Audit;
import com.prepreproject.member.entity.Member;
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
@Table(name = "ORDERS")
public class Order extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;

    public Order(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public enum OrderStatus {
        ORDER_REQUEST(1, "주문 요청"),
        ORDER_CONFIRM(2, "주문 확정"),
        ORDER_COMPLETE(3, "주문 처리 완료"),
        ORDER_CANCEL(4, "주문 취소");

        @Getter
        private int stepNumber;

        @Getter
        private String status;

        OrderStatus(int stepNumber, String status) {
            this.stepNumber = stepNumber;
            this.status = status;
        }
    }

    // 엔티티 연관관계 매핑
    // 1. member(1) -> 객체 하나
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID") // 테이블에서의 외래키 컬럼명
    private Member member;

    public void setMember(Member member) {
        this.member = member;
    }

    // OrderCoffee와 매핑
    @OneToMany(mappedBy = "order")
    private List<OrderCoffee> orderCoffees = new ArrayList<>();

    public void setOrderCoffee(OrderCoffee orderCoffee) {
        orderCoffees.add(orderCoffee);
        if(orderCoffee.getOrder() != this) {
            orderCoffee.setOrder(this);
        }
    }

}
