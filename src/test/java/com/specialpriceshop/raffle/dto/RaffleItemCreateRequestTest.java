package com.specialpriceshop.raffle.dto;

import static com.specialpriceshop.fixture.raffle.RaffleItemCreateRequestFixture.createRaffleItemCreateRequest;
import static com.specialpriceshop.fixture.StockCreateRequestFixture.createStockCreateRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.specialpriceshop.item.dto.StockCreateRequest;
import com.specialpriceshop.raffle.domain.Raffle;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RaffleItemCreateRequestTest {

    @Test
    @DisplayName("StockCreateRequest가 null인 경우")
    void toEntity_stockCreateRequestNull() {
        final RaffleItemCreateRequest raffleItemCreateRequest = createRaffleItemCreateRequest(
            "상품명",
            "상품설명",
            BigDecimal.valueOf(5000),
            BigDecimal.valueOf(1000.0),
            null
        );

        final Raffle entity = raffleItemCreateRequest.toEntity();

        assertEquals(0, entity.getItem().getStocks().size());
        assertNotNull(entity.getItem().getStocks());
    }

    @Test
    @DisplayName("StockCreateRequest가 null이 아닌 경우")
    void toEntity_stockCreateRequest() {
        final List<StockCreateRequest> stockCreateRequest = List.of(
            createStockCreateRequest("옵션1", 10L, BigDecimal.valueOf(10000.0)),
            createStockCreateRequest("옵션2", 10L, BigDecimal.valueOf(20000.0)),
            createStockCreateRequest("옵션", 10L, BigDecimal.valueOf(30000.0))

        );
        final RaffleItemCreateRequest raffleItemCreateRequest = createRaffleItemCreateRequest(
            "상품명",
            "상품설명",
            BigDecimal.valueOf(5000),
            BigDecimal.valueOf(1000.0),
            stockCreateRequest
        );

        final Raffle entity = raffleItemCreateRequest.toEntity();

        assertNotNull(entity.getItem().getStocks());
    }
}