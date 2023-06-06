package com.specialpriceshop.item.dto;

import com.specialpriceshop.item.domain.Stock;
import java.math.BigDecimal;

public record StockResponse(Long stockId, String optionName, BigDecimal addPrice) {

    public static StockResponse of(Stock entity) {
        return new StockResponse(entity.getId(), entity.getOptionName(), entity.getAddPrice());
    }
}
