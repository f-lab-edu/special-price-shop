package com.specialpriceshop.fixture.item;

import com.specialpriceshop.item.domain.Item;
import java.math.BigDecimal;

public class ItemFixture {

    public static Item create(final String name, BigDecimal price) {
        return new Item(name, "상품설명", price);
    }

}
