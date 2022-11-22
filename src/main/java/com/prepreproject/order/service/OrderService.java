package com.prepreproject.order.service;

import com.prepreproject.order.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    // 주문 생성
    public Order createOrder(Order order) {
        return null;
    }
    // 주문 수정
    public Order updateOrder(Order order) {
        return null;
        // 주문 상태 stepNumber 확인 후 수정 요망
    }
    // 주문 조회
    public Order findOrder(long orderId) {
        return null;
    }
    // 주문 목록 조회
    public List<Order> findOrders(int page, int size) {
        return null;
    }
    // 주문 삭제
    public void deleteOrder(long orderId) {

    }
}
