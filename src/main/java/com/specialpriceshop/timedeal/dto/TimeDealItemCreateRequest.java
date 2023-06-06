package com.specialpriceshop.timedeal.dto;

import com.specialpriceshop.item.domain.Item;
import com.specialpriceshop.item.dto.StockCreateRequest;
import com.specialpriceshop.timedeal.domain.TimeDeal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeDealItemCreateRequest {

    @NotEmpty
    private String itemName;

    @NotNull
    private String itemDescription;

    private BigDecimal itemOriginalPrice;

    private BigDecimal timeDealPrice;

    @NotNull
    @FutureOrPresent
    private LocalDateTime timeDealStartDate;

    @NotNull
    @Future
    private LocalDateTime timeDealEndDate;

    @Valid
    @NotEmpty
    private List<StockCreateRequest> stocks = new ArrayList<>();

    public TimeDealItemCreateRequest(
        final String itemName,
        final String itemDescription,
        final BigDecimal itemOriginalPrice,
        final BigDecimal timeDealPrice,
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
