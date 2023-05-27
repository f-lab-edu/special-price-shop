package com.specialpriceshop.dto;

import com.specialpriceshop.domain.Raffle;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record RaffleItemDetailResponse(Long raffleId,
                                       Long itemId,
                                       String itemName,
                                       String itemDescription,

                                       double itemOriginalPrice,

                                       double rafflePrice,

                                       LocalDateTime raffleStartDate,

                                       LocalDateTime raffleEndDate,

                                       LocalDateTime drawDate,

                                       LocalDateTime paymentDueDate,
                                       Set<StockResponse> stockResponses) {


    public static RaffleItemDetailResponse of(final Raffle entity) {
        return new RaffleItemDetailResponse(
            entity.getId(),
            entity.getItem().getId(),
            entity.getItem().getName(),
            entity.getItem().getDescription(),
            entity.getItem().getOriginalPrice(),
            entity.getRafflePrice(),
            entity.getRaffleTimeInfo().getRaffleStartDate(),
            entity.getRaffleTimeInfo().getRaffleEndDate(),
            entity.getRaffleTimeInfo().getDrawDate(),
            entity.getRaffleTimeInfo().getPaymentDueDate(),
            entity.getItem().getStocks()
                .stream()
                .map(StockResponse::of)
                .collect(Collectors.toUnmodifiableSet()));
    }
}
