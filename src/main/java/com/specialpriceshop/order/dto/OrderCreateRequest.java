package com.specialpriceshop.order.dto;

import com.specialpriceshop.order.domain.Order;
import com.specialpriceshop.order.domain.OrderStock;
import com.specialpriceshop.order.domain.Orderline;
import com.specialpriceshop.order.domain.Payment;
import com.specialpriceshop.timedeal.domain.TimeDeal;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderCreateRequest {

    @NotNull
    private Long stockId;

    @NotNull
    private Long quantity;

    @NotNull
    private String address;

    public OrderCreateRequest(
        final Long stockId,
        final Long quantity,
        final String address
    ) {
        this.stockId = stockId;
        this.quantity = quantity;
        this.address = address;
    }

    public Order toEntity(
        final String userId,
        final TimeDeal timeDeal
    ) {
        final OrderStock orderStock = new OrderStock(quantity, stockId);
        final Orderline orderline = new Orderline(timeDeal.getItem().getId(), orderStock);
        final Payment payment = new Payment(timeDeal.calcAmount(stockId, quantity));

        return Order.timeDealOrder(
            orderline,
            userId,
            address,
            timeDeal.getTimeDealTimeInfo().getDealEndDate(),
            payment
        );
    }
}
