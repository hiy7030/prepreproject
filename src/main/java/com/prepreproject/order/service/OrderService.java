package com.prepreproject.order.service;

import com.prepreproject.order.entity.Order;
import com.prepreproject.order.repositoty.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

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
    public Page<Order> findOrders(int page, int size) {

        Pageable pageable = PageRequest.of(page-1, size, Sort.by("orderId").descending());
        return orderRepository.findAll(pageable);
    }
    // 주문 삭제
    public void deleteOrder(long orderId) {

    }
}
