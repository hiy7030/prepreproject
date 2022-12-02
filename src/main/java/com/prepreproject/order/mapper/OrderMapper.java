package com.prepreproject.order.mapper;

import com.prepreproject.coffee.entity.Coffee;
import com.prepreproject.member.entity.Member;
import com.prepreproject.order.dto.OrderCoffeeDto;
import com.prepreproject.order.dto.OrderDto;
import com.prepreproject.order.entity.Order;
import com.prepreproject.order.entity.OrderCoffee;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    default Order orderPostDtoToOrder(OrderDto.Post orderPostDto) {
        Order order = new Order();

        Member member = new Member();
        member.setMemberId(orderPostDto.getMemberId());
        // orderCoffees
        List<OrderCoffeeDto.Post> orderCoffeePostDto = orderPostDto.getOrderCoffees();
        //  List<OrderCoffeeDto.Post> -> List<OrderCoffee>
        // OrderCoffeeDto.Post -> coffeeId, quantity를 가짐

        List<OrderCoffee> orderCoffees = orderCoffeePostDto
                .stream().map(orderCoffeePost -> {
                    OrderCoffee orderCoffee = new OrderCoffee();
                    Coffee coffee = new Coffee();
                    coffee.setCoffeeId(orderCoffeePost.getCoffeeId());
                    orderCoffee.setCoffee(coffee);
                    orderCoffee.setOrder(order);
                    orderCoffee.setQuantity(orderCoffee.getQuantity());
                    return orderCoffee;
                } ).collect(Collectors.toList());

        order.setMember(member);
        order.setOrderCoffees(orderCoffees);

        return order;
    }
    Order orderPatchDtoToOrder(OrderDto.Patch orderPatchDto);
    default OrderDto.Response orderToOrderResponseDto(Order order) {

        // List<orderCoffee> -> List<OrderCoffeeDto.Response>
        List<OrderCoffee> orderCoffees = order.getOrderCoffees();

        // order -> orderDto
        OrderDto.Response orderResponseDto = new OrderDto.Response();
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setMember(order.getMember());
        orderResponseDto.setOrderStatus(order.getOrderStatus());
        orderResponseDto.setCreatedAt(order.getCreateAt());
        orderResponseDto.setOrderCoffees(orderCoffeeToOrderCoffeeResponseDtos(orderCoffees));

        return orderResponseDto;
    }
    List<OrderDto.Response> ordersToOrderResponseDtos (List<Order> orders);

    default List<OrderCoffeeDto.Response> orderCoffeeToOrderCoffeeResponseDtos(List<OrderCoffee> orderCoffees) {

        return orderCoffees.stream()
                .map(orderCoffee -> OrderCoffeeDto.Response.builder()
                            .coffeeId(orderCoffee.getCoffee().getCoffeeId())
                            .quantity(orderCoffee.getQuantity())
                            .price(orderCoffee.getCoffee().getPrice())
                            .korName(orderCoffee.getCoffee().getKorName())
                            .engName(orderCoffee.getCoffee().getEngName())
                            .build())
                .collect(Collectors.toList());
    }
}
