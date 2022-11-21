package com.prepreproject.order.controller;

import com.prepreproject.order.entity.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class orderController {
    // 주문 등록
    @PostMapping
    public ResponseEntity postOrder() {
        return null;
    }
    // 주문 수정
    @PatchMapping("/{order-id}")
    public ResponseEntity  patchOrder(@PathVariable("order-id") @Positive long orderId) {
        return null;
    }
    // 주문 조회
    @GetMapping("/{order-id}")
    public ResponseEntity  getOrder(@PathVariable("order-id") @Positive long orderId) {
        return null;
    }
    // 주문 목록 조회
    @GetMapping
    public List<Order> getOrders() {
        return null;
    }
    // 주문 취소
    @DeleteMapping("/{order-id}")
    public void deleteOrder(@PathVariable("order-id") @Positive long orderId) {

    }
}
