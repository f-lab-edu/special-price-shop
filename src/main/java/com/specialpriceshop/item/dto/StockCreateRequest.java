package com.specialpriceshop.item.dto;

import com.specialpriceshop.item.domain.Stock;
import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockCreateRequest {

    @NotEmpty
    private String optionName;

    private long quantity;

    private double addPrice;

    public StockCreateRequest(
        final String optionName,
        final long quantity,
        final double addPrice
    ) {
        this.optionName = optionName;
        this.quantity = quantity;
        this.addPrice = addPrice;
    }

    public Stock toEntity() {
        return  Stock.builder()
            .optionName(optionName)
            .quantity(quantity)
            .addPrice(addPrice)
            .build();
    }

}
