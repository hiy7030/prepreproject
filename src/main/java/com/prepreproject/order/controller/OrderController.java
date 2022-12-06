package com.prepreproject.order.controller;

import com.prepreproject.dto.MultiResponseDto;
import com.prepreproject.dto.SingleResponseDto;
import com.prepreproject.member.entity.Member;
import com.prepreproject.member.service.MemberService;
import com.prepreproject.order.dto.OrderPatchDto;
import com.prepreproject.order.dto.OrderPostDto;
import com.prepreproject.order.dto.OrderResponseDto;
import com.prepreproject.order.entity.Order;
import com.prepreproject.order.mapper.OrderMapper;
import com.prepreproject.order.service.OrderService;
import com.prepreproject.response.PageInfo;
import com.prepreproject.stamp.Stamp;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper mapper;

    private final MemberService memberService;
    private final static String ORDER_DEFAULT_URL = "/v1/orders";

    public OrderController(OrderService orderService,
                           OrderMapper mapper,
                           MemberService memberService) {
        this.orderService = orderService;
        this.mapper = mapper;
        this.memberService = memberService;
    }

    // 주문 등록
    @PostMapping
    public ResponseEntity postOrder(@Valid @RequestBody OrderPostDto orderPostDto) {
        // orderPostDto -> memberId, OrderCoffees[]
        Order order = orderService.createOrder(mapper.orderPostDtoToOrder(orderPostDto));
        // response header에 해당 orderId가 담긴 URI 넘겨주기

        URI location = UriComponentsBuilder.newInstance()
                .path(ORDER_DEFAULT_URL + "/{order-id}")
                .buildAndExpand(order.getOrderId())
                .toUri();


        return ResponseEntity.created(location).build();
    }



    // 주문 수정
    @PatchMapping("/{order-id}")
    public ResponseEntity patchOrder(@PathVariable("order-id") @Positive long orderId,
                                      @Valid @RequestBody OrderPatchDto orderPatchDto) {

        Order order = orderService.updateOrder(mapper.orderPatchDtoToOrder(orderPatchDto));
        order.setOrderId(orderId);

        OrderResponseDto response = mapper.orderToOrderResponseDto(order);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    // 주문 조회
    @GetMapping("/{order-id}")
    public ResponseEntity  getOrder(@PathVariable("order-id") @Positive long orderId) {

        Order order = orderService.findOrder(orderId);

        OrderResponseDto response = mapper.orderToOrderResponseDto(order);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    // 주문 목록 조회
    @GetMapping
    public ResponseEntity getOrders(@Positive @RequestParam int page,
                                    @Positive @RequestParam int size) {
        // 페이지 인포
        Page<Order> pageOrder =orderService.findOrders(page, size);
        PageInfo pageInfo = new PageInfo(page, size, (int) pageOrder.getTotalElements(), pageOrder.getTotalPages());

        //멤버정보
        List<Order> orders = pageOrder.getContent();
        List<OrderResponseDto> responses = mapper.ordersToOrderResponseDtos(orders);

        return new ResponseEntity<>(new MultiResponseDto<>(responses, pageInfo), HttpStatus.OK);
    }
    // 주문 취소
    @DeleteMapping("/{order-id}")
    public void deleteOrder(@PathVariable("order-id") @Positive long orderId) {
        orderService.cancelOrder(orderId);
    }
}
