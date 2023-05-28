package com.specialpriceshop.raffle.dto;

import com.specialpriceshop.raffle.domain.Raffle;
import java.time.LocalDateTime;

public record RaffleItemResponse(Long raffleId,
                                 Long itemId,
                                 String itemName,
                                 String itemDescription,

                                 double itemOriginalPrice,

                                 double rafflePrice,

                                 LocalDateTime raffleStartDate,

                                 LocalDateTime raffleEndDate,

                                 LocalDateTime drawDate,

                                 LocalDateTime paymentDueDate) {


    public static RaffleItemResponse of(Raffle entity) {
        return new RaffleItemResponse(
            entity.getId(),
            entity.getItem().getId(),
            entity.getItem().getName(),
            entity.getItem().getDescription(),
            entity.getItem().getOriginalPrice(),
            entity.getRafflePrice(),
            entity.getRaffleTimeInfo().getRaffleStartDate(),
            entity.getRaffleTimeInfo().getRaffleEndDate(),
            entity.getRaffleTimeInfo().getDrawDate(),
            entity.getRaffleTimeInfo().getPaymentDueDate());
    }
}
