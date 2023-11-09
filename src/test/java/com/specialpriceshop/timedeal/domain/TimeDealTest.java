package com.specialpriceshop.timedeal.domain;

import static com.specialpriceshop.fixture.timedeal.TimeDealFixture.createTimeDeal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.specialpriceshop.item.domain.Item;
import com.specialpriceshop.item.domain.Stock;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TimeDealTest {

    @Test
    @DisplayName("입력시간에 타임딜이 진행중이다")
    void isAvailable_progress() {
        final LocalDateTime now = LocalDateTime.now();

        final TimeDeal timeDeal = createTimeDeal(
            BigDecimal.valueOf(1000L),
            now.minusDays(1L),
            now.plusDays(1L),
            new Item("상품", "설명", BigDecimal.valueOf(1000L)));

        assertTrue(timeDeal.isAvailable(LocalDateTime.now()));
    }

    @Test
    @DisplayName("입력시간에 타임딜이 진행중아닌 경우")
    void isAvailable_not_progress() {
        final LocalDateTime now = LocalDateTime.now();

        final TimeDeal timeDeal = createTimeDeal(
            BigDecimal.valueOf(1000L),
            now.minusDays(1L),
            now.plusDays(1L),
            new Item("상품", "설명", BigDecimal.valueOf(1000L)));

        assertFalse(timeDeal.isAvailable(now.minusDays(2L)));
    }

    @Test
    @DisplayName("상품금액을 계산한다.")
    void calcAmount() {
        final LocalDateTime now = LocalDateTime.now();
        final long stockId = 1L;
        final long orderQuantity = 10L;
        final BigDecimal timeDealPrice = BigDecimal.valueOf(1000L);
        final BigDecimal optionAddPrice = BigDecimal.valueOf(100L);

        final Item fixtureItem = new Item("상품", "설명", BigDecimal.valueOf(10000L));
        fixtureItem.addStock(Stock.builder()
            .id(stockId)
            .optionName("옵션1")
            .addPrice(optionAddPrice)
            .build());

        final TimeDeal timeDeal = createTimeDeal(
            timeDealPrice,
            now.minusDays(1L),
            now.plusDays(1L),
            fixtureItem
        );

        final BigDecimal result = timeDealPrice.add(optionAddPrice)
            .multiply(BigDecimal.valueOf(orderQuantity));

        assertEquals(timeDeal.calcAmount(stockId, orderQuantity), result);
    }
}
