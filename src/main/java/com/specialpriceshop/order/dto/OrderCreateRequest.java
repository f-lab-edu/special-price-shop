package com.specialpriceshop.order.dto;

import com.specialpriceshop.order.domain.Order;
import com.specialpriceshop.order.domain.OrderStock;
import com.specialpriceshop.order.domain.Orderline;
import java.time.LocalDateTime;
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
        final Long itemId,
        final String userId,
        final LocalDateTime timeDealEndDate
    ) {
        final OrderStock orderStock = new OrderStock(quantity, stockId);
        final Orderline orderline = new Orderline(itemId, orderStock);
        return Order.timeDealOrder(
            orderline,
            userId,
            address,
            timeDealEndDate
        );
    }
}
