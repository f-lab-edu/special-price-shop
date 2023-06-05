package com.specialpriceshop.fixture;

import com.specialpriceshop.item.dto.StockCreateRequest;
import java.math.BigDecimal;

public class StockCreateRequestFixture {

    public static StockCreateRequest createStockCreateRequest(
        final String optionName,
        final long quantity,
        final BigDecimal addPrice
    ) {
        return new StockCreateRequest(optionName,quantity,addPrice);
    }

}
