package com.prepreproject.order.mapper;

import com.prepreproject.order.dto.OrderDto;
import com.prepreproject.order.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order orderPostDtoToOrder(OrderDto.Post orderPostDto);
    Order orderPatchDtoToOrder(OrderDto.Patch orderPatchDto);
    OrderDto.Response orderToOrderResponseDto(Order order);
}
