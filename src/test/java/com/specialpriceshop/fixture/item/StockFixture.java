package com.specialpriceshop.fixture.item;

import com.specialpriceshop.item.domain.Stock;
import java.math.BigDecimal;

public class StockFixture {

    public static Stock create(
        final Long id,
        final Long quantity,
        final String optionName,
        final BigDecimal addprice) {
        return Stock.builder()
            .id(id)
            .quantity(quantity)
            .optionName(optionName)
            .addPrice(addprice)
            .build();
    }
}
