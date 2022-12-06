package com.prepreproject.order.mapper;

import com.prepreproject.coffee.entity.Coffee;
import com.prepreproject.member.entity.Member;
import com.prepreproject.order.dto.*;
import com.prepreproject.order.entity.Order;
import com.prepreproject.order.entity.OrderCoffee;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    default Order orderPostDtoToOrder(OrderPostDto orderPostDto) {
        Order order = new Order();

        Member member = new Member();
        member.setMemberId(orderPostDto.getMemberId());
        // orderCoffees
        //  List<OrderCoffeeDto.Post> -> List<OrderCoffee>
        // OrderCoffeeDto.Post -> coffeeId, quantity를 가짐

        List<OrderCoffee> orderCoffees = orderPostDto.getOrderCoffees()
                .stream().map(orderCoffeePost -> {
                    OrderCoffee orderCoffee = new OrderCoffee();
                    Coffee coffee = new Coffee();
                    coffee.setCoffeeId(orderCoffeePost.getCoffeeId());
                    orderCoffee.addCoffee(coffee);
                    orderCoffee.addOrder(order);
                    orderCoffee.setQuantity(orderCoffeePost.getQuantity());
                    return orderCoffee;
                } ).collect(Collectors.toList());

        order.addMember(member);
        order.setOrderCoffees(orderCoffees);

        return order;
    }
    Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto);
    default OrderResponseDto orderToOrderResponseDto(Order order) {

        // List<orderCoffee> -> List<OrderCoffeeDto.Response>
        List<OrderCoffee> orderCoffees = order.getOrderCoffees();

        // order -> orderDto
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setMember(order.getMember());
        orderResponseDto.setOrderStatus(order.getOrderStatus());
        orderResponseDto.setCreateAt(order.getCreateAt());
        orderResponseDto.setOrderCoffees(orderCoffeeToOrderCoffeeResponseDtos(orderCoffees));

        return orderResponseDto;
    }
    List<OrderResponseDto> ordersToOrderResponseDtos (List<Order> orders);

    default List<OrderCoffeeResponseDto> orderCoffeeToOrderCoffeeResponseDtos(List<OrderCoffee> orderCoffees) {

        return orderCoffees.stream()
                .map(orderCoffee -> OrderCoffeeResponseDto.builder()
                            .coffeeId(orderCoffee.getCoffee().getCoffeeId())
                            .quantity(orderCoffee.getQuantity())
                            .price(orderCoffee.getCoffee().getPrice())
                            .korName(orderCoffee.getCoffee().getKorName())
                            .engName(orderCoffee.getCoffee().getEngName())
                            .build())
                .collect(Collectors.toList());
    }
}
