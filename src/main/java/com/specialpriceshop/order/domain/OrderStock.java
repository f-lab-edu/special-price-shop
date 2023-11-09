package com.specialpriceshop.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderStock {

    private Long quantity;

    private Long stockId;

    public OrderStock(
        final Long quantity,
        final Long stockId) {
        this.quantity = quantity;
        this.stockId = stockId;
    }
}
