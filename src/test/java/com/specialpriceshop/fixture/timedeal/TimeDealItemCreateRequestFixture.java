package com.specialpriceshop.fixture.timedeal;

import com.specialpriceshop.item.dto.StockCreateRequest;
import com.specialpriceshop.timedeal.dto.TimeDealItemCreateRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TimeDealItemCreateRequestFixture {

    public static TimeDealItemCreateRequest createTimedealItemCreateRequest(
        final String itemName,
        final String itemDescription,
        final BigDecimal itemOriginalPrice,
        final BigDecimal timeDealPrice,
        final LocalDateTime timeDealStartDate,
        final LocalDateTime timeDealEndDate,
        final List<StockCreateRequest> stocks
    ) {
        return new TimeDealItemCreateRequest(
            itemName,
            itemDescription,
            itemOriginalPrice,
            timeDealPrice,
            timeDealStartDate,
            timeDealEndDate,
            stocks
        );
    }

    public static TimeDealItemCreateRequest createTimedealItemCreateRequest(
        final String itemName,
        final String itemDescription,
        final BigDecimal itemOriginalPrice,
        final BigDecimal timeDealPrice,
        final List<StockCreateRequest> stocks
    ) {
        return new TimeDealItemCreateRequest(
            itemName,
            itemDescription,
            itemOriginalPrice,
            timeDealPrice,
            LocalDateTime.now().plusHours(1L),
            LocalDateTime.now().plusDays(3L),
            stocks
        );
    }

}
