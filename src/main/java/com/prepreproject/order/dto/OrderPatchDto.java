package com.prepreproject.order.dto;

import com.prepreproject.order.entity.Order;
import lombok.Getter;

@Getter
public class OrderPatchDto {

    private long orderId;
    private Order.OrderStatus orderStatus;

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
