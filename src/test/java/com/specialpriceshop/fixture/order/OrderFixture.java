package com.specialpriceshop.fixture.order;

import com.specialpriceshop.order.domain.Order;
import com.specialpriceshop.order.domain.Order.OrderBuilder;
import com.specialpriceshop.order.domain.OrderStatus;
import com.specialpriceshop.order.domain.OrderType;

public class OrderFixture {

    public static OrderBuilder create() {
        return Order.builder()
            .orderType(OrderType.RAFFLE)
            .orderStatus(OrderStatus.ORDER);
    }

}
