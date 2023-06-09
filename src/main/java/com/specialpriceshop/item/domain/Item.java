package com.specialpriceshop.item.domain;

import com.specialpriceshop.common.entity.BaseTimeEntity;
import com.specialpriceshop.item.exception.NotFoundStockException;
import com.specialpriceshop.item.exception.OutOfStockException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal originalPrice;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Stock> stocks = new HashSet<>();

    @Builder
    public Item(
        final String name,
        final String description,
        final BigDecimal originalPrice
    ) {
        this.name = name;
        this.description = description;
        this.originalPrice = originalPrice;
    }

    public void addStock(final Stock stock) {
        if (stock != null) {
            this.stocks.add(stock);
            stock.with(this);
        }
    }

    public BigDecimal calcAmount(
        final Long stockId,
        final BigDecimal timeDealPrice,
        final long quantity
    ) {
        return timeDealPrice
            .add(findStocks(stockId).getAddPrice())
            .multiply(BigDecimal.valueOf(quantity));
    }

    public void isOrderable(
        final Long stockId,
        final long orderQuantity
    ) {
        if (!findStocks(stockId).isOrderable(orderQuantity)) {
            throw new OutOfStockException();
        }
    }

    private Stock findStocks(final Long stockId) {

        return stocks
            .stream()
            .filter(s -> s.getId().equals(stockId))
            .findFirst()
            .orElseThrow(NotFoundStockException::new);
    }
}
