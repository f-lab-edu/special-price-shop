package com.specialpriceshop.fixture.timedeal;

import com.specialpriceshop.item.domain.Item;
import com.specialpriceshop.timedeal.domain.TimeDeal;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TimeDealFixture {

    public static TimeDeal createTimeDeal(
        final BigDecimal timeDealPrice,
        final LocalDateTime timeDealStartDate,
        final LocalDateTime timeDealEndDate,
        final Item item
    ) {
        return new TimeDeal(
            timeDealPrice,
            timeDealStartDate,
            timeDealEndDate,
            item
        );
    }

}
