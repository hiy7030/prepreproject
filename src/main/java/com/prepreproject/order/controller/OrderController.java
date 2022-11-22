package com.prepreproject.order.controller;

import com.prepreproject.dto.MultiResponseDto;
import com.prepreproject.dto.SingleResponseDto;
import com.prepreproject.order.dto.OrderDto;
import com.prepreproject.order.mapper.OrderMapper;
import com.prepreproject.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

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

        return new ResponseEntity<>(new SingleResponseDto<>(), HttpStatus.CREATED);
    }
    // 주문 수정
    @PatchMapping("/{order-id}")
    public ResponseEntity  patchOrder(@PathVariable("order-id") @Positive long orderId,
                                      @Valid @RequestBody OrderDto.Patch orderDtoPatch) {

        return new ResponseEntity<>(new SingleResponseDto<>(), HttpStatus.OK);
    }
    // 주문 조회
    @GetMapping("/{order-id}")
    public ResponseEntity  getOrder(@PathVariable("order-id") @Positive long orderId) {

        return new ResponseEntity<>(new SingleResponseDto<>(), HttpStatus.OK);
    }
    // 주문 목록 조회
    @GetMapping
    public ResponseEntity getOrders(@RequestParam int page,
                                    @RequestParam int size) {

        return new ResponseEntity<>(new MultiResponseDto<>(), HttpStatus.OK);
    }
    // 주문 취소
    @DeleteMapping("/{order-id}")
    public void deleteOrder(@PathVariable("order-id") @Positive long orderId) {

    }
}
