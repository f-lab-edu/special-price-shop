package com.specialpriceshop.timedeal.dto;

import com.specialpriceshop.timedeal.domain.TimeDeal;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TimeDealItemResponse(Long timeDealId,
                                   Long itemId,
                                   String itemName,
                                   String itemDescription,
                                   BigDecimal itemOriginalPrice,
                                   BigDecimal timeDealPrice,
                                   LocalDateTime dealStartDate,
                                   LocalDateTime dealEndDate
) {

    public static TimeDealItemResponse of(final TimeDeal entity) {
        return new TimeDealItemResponse(
            entity.getId(),
            entity.getItem().getId(),
            entity.getItem().getName(),
            entity.getItem().getDescription(),
            entity.getItem().getOriginalPrice(),
            entity.getTimeDealPrice(),
            entity.getTimeDealTimeInfo().getDealStartDate(),
            entity.getTimeDealTimeInfo().getDealEndDate());
    }
}
