package com.prepreproject.order.service;

import com.prepreproject.coffee.entity.Coffee;
import com.prepreproject.coffee.service.CoffeeService;
import com.prepreproject.member.service.MemberService;
import com.prepreproject.order.entity.Order;
import com.prepreproject.order.entity.OrderCoffee;
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
    private final MemberService memberService;
    private final CoffeeService coffeeService;

    public OrderService(OrderRepository orderRepository,
                        MemberService memberService,
                        CoffeeService coffeeService) {
        this.orderRepository = orderRepository;
        this.memberService = memberService;
        this.coffeeService = coffeeService;
    }

    // 주문 생성
    public Order createOrder(Order order) {
        // 회원 존재 검증 후 저장
        memberService.findVerifiedMember(order.getMember().getMemberId());
        //orderCoffee 가 존재하는 지 검증
        List<OrderCoffee> orderCoffees = order.getOrderCoffees();
        orderCoffees.stream().forEach(orderCoffee ->
                coffeeService.findVerifiedCoffee(orderCoffee.getCoffee().getCoffeeId()));

        // orderCoffee의 각 quantity 합계를 구해 StampCount 하고 member.setStamp;

        return orderRepository.save(order);
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
