package com.specialpriceshop.fixture;

import com.specialpriceshop.item.dto.StockCreateRequest;
import com.specialpriceshop.timedeal.dto.TimeDealItemCreateRequest;
import java.time.LocalDateTime;
import java.util.List;

public class TimeDealItemCreateRequestFixture {

    public static TimeDealItemCreateRequest createTimedealItemCreateRequest(
        final String itemName,
        final String itemDescription,
        final double itemOriginalPrice,
        final double timeDealPrice,
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
        final double itemOriginalPrice,
        final double timeDealPrice,
        final List<StockCreateRequest> stocks
    ) {
        return new TimeDealItemCreateRequest(
            itemName,
            itemDescription,
            itemOriginalPrice,
            timeDealPrice,
            LocalDateTime.now(),
            LocalDateTime.now().plusDays(3L),
            stocks
        );
    }

}
