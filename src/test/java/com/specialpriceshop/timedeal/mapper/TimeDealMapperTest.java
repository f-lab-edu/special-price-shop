package com.specialpriceshop.timedeal.mapper;

import static com.specialpriceshop.fixture.StockCreateRequestFixture.createStockCreateRequest;
import static com.specialpriceshop.fixture.timedeal.TimeDealItemCreateRequestFixture.createTimedealItemCreateRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.specialpriceshop.item.dto.StockCreateRequest;
import com.specialpriceshop.item.mapper.StockMapper;
import com.specialpriceshop.timedeal.domain.TimeDeal;
import com.specialpriceshop.timedeal.dto.TimeDealItemCreateRequest;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TimeDealMapperTest {

    private TimeDealMapper timeDealMapper;

    @BeforeEach
    void init() {
        timeDealMapper = new TimeDealMapper(new StockMapper());
    }

    @Test
    @DisplayName("StockCreateRequest가 null인 경우")
    void toEntity_stockCreateRequestNull() {
        final TimeDealItemCreateRequest request = createTimedealItemCreateRequest(
            "상품명",
            "상품설명",
            BigDecimal.valueOf(5000),
            BigDecimal.valueOf(1000.0),
            null);

        final TimeDeal entity = timeDealMapper.createRequestToEntity(request);

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

        final TimeDealItemCreateRequest request = createTimedealItemCreateRequest(
            "상품명",
            "상품설명",
            BigDecimal.valueOf(5000),
            BigDecimal.valueOf(1000.0),
            stockCreateRequest);

        final TimeDeal entity = timeDealMapper.createRequestToEntity(request);

        assertNotNull(entity.getItem().getStocks());
    }
}