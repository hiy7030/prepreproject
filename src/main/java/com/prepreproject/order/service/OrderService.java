package com.prepreproject.order.service;

import com.prepreproject.order.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    // 주문 생성
    public Order createOrder(Order order) {
        order.setOrderId(1L);
        return order;
    }
    // 주문 수정
    public Order updateOrder(Order order) {

        return order;
        // 주문 상태 stepNumber 확인 후 수정 요망
    }
    // 주문 조회
    public Order findOrder(long orderId) {
        Order order = new Order(Order.OrderStatus.ORDER_REQUEST);
        order.setOrderId(orderId);
        return order;
    }
    // 주문 목록 조회
    public List<Order> findOrders() {

        Order order1 = new Order(Order.OrderStatus.ORDER_REQUEST);
        order1.setOrderId(1L);

        Order order2 = new Order(Order.OrderStatus.ORDER_REQUEST);
        order2.setOrderId(2L);

        return List.of(order1, order2);
    }
    // 주문 삭제
    public void deleteOrder(long orderId) {

    }
}
