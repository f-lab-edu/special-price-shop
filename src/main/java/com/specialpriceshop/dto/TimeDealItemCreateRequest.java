package com.specialpriceshop.dto;

import com.specialpriceshop.domain.Item;
import com.specialpriceshop.domain.TimeDeal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeDealItemCreateRequest {

    private String itemName;

    private String itemDescription;

    private double itemOriginalPrice;

    private double timeDealPrice;

    private LocalDateTime timeDealStartDate;

    private LocalDateTime timeDealEndDate;

    private List<StockCreateRequest> stocks = new ArrayList<>();

    public TimeDealItemCreateRequest(
        final String itemName,
        final String itemDescription,
        final double itemOriginalPrice,
        final double timeDealPrice,
        final LocalDateTime timeDealStartDate,
        final LocalDateTime timeDealEndDate,
        final List<StockCreateRequest> stocks) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemOriginalPrice = itemOriginalPrice;
        this.timeDealPrice = timeDealPrice;
        this.timeDealStartDate = timeDealStartDate;
        this.timeDealEndDate = timeDealEndDate;
        this.stocks = stocks;
    }

    public TimeDeal toEntity() {
        final Item item = Item.builder()
            .name(itemName)
            .description(itemDescription)
            .originalPrice(itemOriginalPrice)
            .build();

        Stream.ofNullable(stocks)
            .flatMap(Collection::stream)
            .map(StockCreateRequest::toEntity)
            .forEach(item::addStock);

        return TimeDeal.builder()
            .timeDealPrice(timeDealPrice)
            .timeDealStartDate(timeDealStartDate)
            .timeDealEndDate(timeDealEndDate)
            .item(item)
            .build();
    }

}
