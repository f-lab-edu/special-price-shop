package com.specialpriceshop.item.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.specialpriceshop.fixture.item.ItemFixture;
import com.specialpriceshop.fixture.item.StockFixture;
import com.specialpriceshop.item.exception.NotFoundStockException;
import com.specialpriceshop.item.exception.OutOfStockException;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItemTest {

    @Test
    @DisplayName("상품에 옵션을 추가 한다.")
    void addStock() {
        final Item item = ItemFixture.create("상품", BigDecimal.valueOf(10000));
        final Stock stock1 = StockFixture.create(1L, 10L, "옵션1", BigDecimal.valueOf(1000));
        final Stock stock2 = StockFixture.create(2L, 10L, "옵션2", BigDecimal.valueOf(1000));
        final Stock stock3 = StockFixture.create(3L, 10L, "옵션3", BigDecimal.valueOf(1000));

        item.addStock(stock1);
        item.addStock(stock2);
        item.addStock(stock3);

        assertTrue(item.getStocks().contains(stock1));
        assertTrue(item.getStocks().contains(stock2));
        assertTrue(item.getStocks().contains(stock3));
        assertEquals(3, item.getStocks().size());
    }

    @Test
    @DisplayName("빈 Stock객체는 추가 하지 않는다")
    void addStock_stockIsEmpty() {
        final Item item = ItemFixture.create("상품", BigDecimal.valueOf(10000));
        final Stock stock = null;
        item.addStock(stock);

        assertEquals(0, item.getStocks().size());
    }

    @Test
    @DisplayName("주문한상품의 가격을 계산한다.")
    void calcAmount() {
        final Long stockId = 1L;
        final BigDecimal price = BigDecimal.valueOf(100);

        final Long quantity = 4L;
        final Item item = ItemFixture.create("상품", BigDecimal.valueOf(10000));
        final Stock stock = StockFixture.create(1L, 10L, "옵션1", BigDecimal.valueOf(1000));
        item.addStock(stock);

        assertEquals(item.calcAmount(stockId, price, quantity),
            price.add(stock.getAddPrice()).multiply(
                BigDecimal.valueOf(quantity)));
    }

    @Test
    @DisplayName("존재하지 않는 재고일 경우 throw 한다")
    void calcAmount_empty_stock() {
        final Long stockId = 1L;
        final BigDecimal price = BigDecimal.valueOf(100);

        final Long quantity = 4L;
        final Item item = ItemFixture.create("상품", BigDecimal.valueOf(10000));

        assertThrows(NotFoundStockException.class, () -> item.calcAmount(stockId, price, quantity));
    }

    @Test
    @DisplayName("주문가능한 재고인지 검증한다")
    void isOrderable() {
        final long stockId = 1L;
        final long orderQuantity = 5L;

        final Item item = ItemFixture.create("상품", BigDecimal.valueOf(10000));
        final Stock stock = StockFixture.create(stockId, 10L, "옵션1", BigDecimal.valueOf(1000));
        item.addStock(stock);

        assertDoesNotThrow(() -> item.isOrderable(stockId, orderQuantity));
    }

    @Test
    @DisplayName("주문가능한 재고가 아닐경우 throw한다")
    void isOrderable_outOfStock() {
        final long stockId = 1L;
        final long quantity = 10L;

        final Item item = ItemFixture.create("상품", BigDecimal.valueOf(10000));
        final Stock stock = StockFixture.create(stockId, quantity, "옵션1", BigDecimal.valueOf(1000));
        item.addStock(stock);

        assertThrows(OutOfStockException.class,() -> item.isOrderable(stockId, quantity + 1));
    }
}