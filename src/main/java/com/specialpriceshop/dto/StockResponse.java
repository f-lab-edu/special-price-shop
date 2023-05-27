package com.specialpriceshop.dto;

import com.specialpriceshop.domain.Stock;

public record StockResponse(Long stockId, String optionName, double addPrice) {

    public static StockResponse of(Stock entity) {
        return new StockResponse(entity.getId(), entity.getOptionName(), entity.getAddPrice());
    }
}
