package com.specialpriceshop.fixture;

import com.specialpriceshop.item.dto.StockCreateRequest;

public class StockCreateRequestFixture {

    public static StockCreateRequest createStockCreateRequest(
        final String optionName,
        final long quantity,
        final double addPrice
    ) {
        return new StockCreateRequest(optionName,quantity,addPrice);
    }

}
