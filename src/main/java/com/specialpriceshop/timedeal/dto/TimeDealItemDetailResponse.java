package com.specialpriceshop.timedeal.dto;

import com.specialpriceshop.item.dto.StockResponse;
import com.specialpriceshop.timedeal.domain.TimeDeal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record TimeDealItemDetailResponse(Long timeDealId,
                                         Long itemId,
                                         String itemName,
                                         String itemDescription,
                                         double itemOriginalPrice,
                                         double timeDealPrice,
                                         LocalDateTime dealStartDate,
                                         LocalDateTime dealEndDate,
                                         Set<StockResponse> stockResponses) {

    public static TimeDealItemDetailResponse of(final TimeDeal entity) {
        return new TimeDealItemDetailResponse(
            entity.getId(),
            entity.getItem().getId(),
            entity.getItem().getName(),
            entity.getItem().getDescription(),
            entity.getItem().getOriginalPrice(),
            entity.getTimeDealPrice(),
            entity.getTimeDealTimeInfo().getDealStartDate(),
            entity.getTimeDealTimeInfo().getDealEndDate(),
            entity.getItem().getStocks()
                .stream()
                .map(StockResponse::of)
                .collect(Collectors.toUnmodifiableSet()));
    }

}
