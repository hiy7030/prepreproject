package com.prepreproject.order.service;

import com.prepreproject.coffee.entity.Coffee;
import com.prepreproject.coffee.service.CoffeeService;
import com.prepreproject.exception.BusinessLogicException;
import com.prepreproject.exception.ExceptionCode;
import com.prepreproject.member.entity.Member;
import com.prepreproject.member.service.MemberService;
import com.prepreproject.order.entity.Order;
import com.prepreproject.order.entity.OrderCoffee;
import com.prepreproject.order.repositoty.OrderRepository;
import com.prepreproject.stamp.Stamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        verifyExistOrder(order);
        // orderCoffee의 각 quantity 합계를 구해 StampCount 하고 member.setStamp;
        updateStamp(order);

        return orderRepository.save(order);
    }
    // 주문 수정
    public Order updateOrder(Order order) {
        // 변경 가능한 것 orderStatus!
        // orderId와 맞는 정보 가져오기
        Order findOrder = findVerifiedOrder(order.getOrderId());

        Optional.ofNullable(order.getOrderStatus())
                .ifPresent(orderStatus -> findOrder.setOrderStatus(orderStatus));

        return orderRepository.save(findOrder);
    }
    // 주문 조회
    public Order findOrder(long orderId) {
        return findVerifiedOrder(orderId);
    }
    // 주문 목록 조회
    public Page<Order> findOrders(int page, int size) {

        Pageable pageable = PageRequest.of(page-1, size, Sort.by("orderId").descending());
        return orderRepository.findAll(pageable);
    }
    // 주문 삭제
    public void cancelOrder(long orderId) {
        // order 찾아오기
        Order findOrder = findVerifiedOrder(orderId);
        // 주문 상태 stepNumber 확인 후 수정 요망
        // step 1일 때만 취소가 가능 그 외의 경우에는 Exception 보내기
        int step = findOrder.getOrderStatus().getStep();
        if(step >= 2) {
            throw new BusinessLogicException(ExceptionCode.CANNOT_CANCEL_ORDER);
        }
        // 주문 상태 변경
        findOrder.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);

        orderRepository.delete(findOrder);
    }

    // 검증 메서드
    // 1. orderId로 조회 후 찾은 order 반환
    public Order findVerifiedOrder(long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        Order findOrder = optionalOrder.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND)
        );

        return findOrder;
    }

    //2. orderCoffee 리스트에 Coffee가 존재하는 지 검증
    private void verifyExistOrder(Order order) {
        // 회원 존재 검증 후 저장
        memberService.findVerifiedMember(order.getMember().getMemberId());

        List<OrderCoffee> orderCoffees = order.getOrderCoffees();
        orderCoffees.stream().forEach(orderCoffee ->
                coffeeService.findVerifiedCoffee(orderCoffee.getCoffee().getCoffeeId()));
    }

    // update stampCount
    public void updateStamp(Order order) {
        // 오더에서 멤버 객체 가져오기
        Member member = memberService.findMember(order.getMember().getMemberId());
        // 멤버 객체에서 스탬프 객체 가져오기 스탬프가 null 이면 새 stamp 객체 생성
        if(member.getStamp() == null) {
            Stamp stamp = new Stamp();
        } else {
            Stamp stamp = member.getStamp();
        }
        Stamp stamp = new Stamp();
        // order에서 OrderCoffee 가져와서 각 quantity 개수 합치기
        int newStamp = order.getOrderCoffees()
                .stream()
                .map(orderCoffee -> orderCoffee.getQuantity())
                .mapToInt(quantity -> quantity)
                .sum();
        // 현재 스탬프 갯수와 합쳐 멤버에 set하고 -> 리포지토리에 저장
        stamp.setStampCount(stamp.getStampCount() + newStamp);
        member.setStamp(stamp);
        memberService.updateMember(member);
    }

}
