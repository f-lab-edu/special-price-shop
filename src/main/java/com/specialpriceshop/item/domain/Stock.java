package com.specialpriceshop.item.domain;

import com.specialpriceshop.common.entity.BaseTimeEntity;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String optionName;

    private long quantity;

    private double addPrice;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public Stock(
        final Long id,
        final String optionName,
        final long quantity,
        final double addPrice
    ) {
        this.optionName = optionName;
        this.quantity = quantity;
        this.addPrice = addPrice;
    }

    public void with(Item item) {
        if (this.item != item) {
            this.item = item;
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Stock stock = (Stock) o;
        return id != null && Objects.equals(id, stock.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
