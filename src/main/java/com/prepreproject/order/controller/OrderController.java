package com.prepreproject.order.controller;

import com.prepreproject.dto.MultiResponseDto;
import com.prepreproject.dto.SingleResponseDto;
import com.prepreproject.order.dto.OrderDto;
import com.prepreproject.order.entity.Order;
import com.prepreproject.order.mapper.OrderMapper;
import com.prepreproject.order.service.OrderService;
import com.prepreproject.response.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper mapper;

    public OrderController(OrderService orderService,
                           OrderMapper mapper) {
        this.orderService = orderService;
        this.mapper = mapper;
    }

    // 주문 등록
    @PostMapping
    public ResponseEntity postOrder(@Valid @RequestBody OrderDto.Post orderPostDto) {

        Order order = orderService.createOrder(mapper.orderPostDtoToOrder(orderPostDto));
        OrderDto.Response response = mapper.orderToOrderResponseDto(order);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }
    // 주문 수정
    @PatchMapping("/{order-id}")
    public ResponseEntity  patchOrder(@PathVariable("order-id") @Positive long orderId,
                                      @Valid @RequestBody OrderDto.Patch orderPatchDto) {

        Order order = orderService.updateOrder(mapper.orderPatchDtoToOrder(orderPatchDto));
        order.setOrderId(orderId);

        OrderDto.Response response = mapper.orderToOrderResponseDto(order);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    // 주문 조회
    @GetMapping("/{order-id}")
    public ResponseEntity  getOrder(@PathVariable("order-id") @Positive long orderId) {

        Order order = orderService.findOrder(orderId);

        OrderDto.Response response = mapper.orderToOrderResponseDto(order);

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
        List<OrderDto.Response> responses = mapper.ordersToOrderResponseDtos(orders);

        return new ResponseEntity<>(new MultiResponseDto<>(responses, pageInfo), HttpStatus.OK);
    }
    // 주문 취소
    @DeleteMapping("/{order-id}")
    public void deleteOrder(@PathVariable("order-id") @Positive long orderId) {

    }
}
