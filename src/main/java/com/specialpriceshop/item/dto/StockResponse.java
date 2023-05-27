package com.specialpriceshop.item.dto;

import com.specialpriceshop.item.domain.Stock;

public record StockResponse(Long stockId, String optionName, double addPrice) {

    public static StockResponse of(Stock entity) {
        return new StockResponse(entity.getId(), entity.getOptionName(), entity.getAddPrice());
    }
}
