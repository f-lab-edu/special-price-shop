package com.specialpriceshop.domain;

import com.specialpriceshop.common.entity.BaseTimeEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
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

    public Stock(
        final String optionName,
        final long quantity,
        final double addPrice
    ) {
        this.optionName = optionName;
        this.quantity = quantity;
        this.addPrice = addPrice;
    }

    public void with(final Item item) {
        if (this.item != null) {
            this.item.removeStock(this);
        }
        this.item = item;
        item.addStock(this);
    }
}
