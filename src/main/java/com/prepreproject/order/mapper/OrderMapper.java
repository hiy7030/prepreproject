package com.prepreproject.order.mapper;

import com.prepreproject.member.entity.Member;
import com.prepreproject.order.dto.OrderCoffeeDto;
import com.prepreproject.order.dto.OrderDto;
import com.prepreproject.order.entity.Order;
import com.prepreproject.order.entity.OrderCoffee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    default Order orderPostDtoToOrder(OrderDto.Post orderPostDto) {
        Order order = new Order();

        Member member = new Member();
        orderPostDto.getMemberId();
        member.setMemberId(orderPostDto.getMemberId());
        // orderCoffees
        List<OrderCoffeeDto.Post> orderCoffeeDto = orderPostDto.getOrderCoffees();
        // orderCoffeeDto -> OrderCoffee


        order.setMember(member);

        return order;
    }
    Order orderPatchDtoToOrder(OrderDto.Patch orderPatchDto);
    OrderDto.Response orderToOrderResponseDto(Order order);
    List<OrderDto.Response> ordersToOrderResponseDtos (List<Order> orders);
}
