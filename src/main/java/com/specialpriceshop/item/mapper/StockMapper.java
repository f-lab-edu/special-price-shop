package com.specialpriceshop.item.mapper;

import com.specialpriceshop.item.domain.Stock;
import com.specialpriceshop.item.dto.StockCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    public Stock createRequestToEntity(final StockCreateRequest request) {
        return Stock.builder()
            .optionName(request.getOptionName())
            .quantity(request.getQuantity())
            .addPrice(request.getAddPrice())
            .build();
    }
}