package com.specialpriceshop.fixture.raffle;

import com.specialpriceshop.raffle.dto.RaffleItemCreateRequest;
import com.specialpriceshop.item.dto.StockCreateRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class RaffleItemCreateRequestFixture {

    public static RaffleItemCreateRequest createRaffleItemCreateRequest(
        final String itemName,
        final String itemDescription,
        final BigDecimal itemOriginalPrice,
        final BigDecimal rafflePrice,
        final List<StockCreateRequest> stocks
    ) {
        return new RaffleItemCreateRequest(
            itemName,
            itemDescription,
            itemOriginalPrice,
            rafflePrice,
            LocalDateTime.now().minusMonths(1L),
            LocalDateTime.now().minusMonths(1L).plusDays(10L),
            LocalDateTime.now().minusMonths(1L).plusDays(10L).plusHours(1L),
            LocalDateTime.now().minusMonths(1L).plusDays(10L).plusHours(2L),
            stocks);
    }

    public static RaffleItemCreateRequest createRaffleItemCreateRequest(
        final String itemName,
        final String itemDescription,
        final BigDecimal itemOriginalPrice,
        final BigDecimal rafflePrice,
        final LocalDateTime raffleStartDate,
        final LocalDateTime raffleEndDate,
        final LocalDateTime drawDate,
        final LocalDateTime paymentDueDate,
        final List<StockCreateRequest> stocks) {
        return new RaffleItemCreateRequest(
            itemName,
            itemDescription,
            itemOriginalPrice,
            rafflePrice,
            raffleStartDate,
            raffleEndDate,
            drawDate,
            paymentDueDate,
            stocks);
    }

}
