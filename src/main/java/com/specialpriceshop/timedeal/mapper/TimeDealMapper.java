package com.specialpriceshop.timedeal.mapper;

import com.specialpriceshop.item.domain.Item;
import com.specialpriceshop.item.mapper.StockMapper;
import com.specialpriceshop.timedeal.domain.TimeDeal;
import com.specialpriceshop.timedeal.dto.TimeDealItemCreateRequest;
import java.util.Collection;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TimeDealMapper {

    private final StockMapper stockMapper;

    public TimeDeal createRequestToEntity(final TimeDealItemCreateRequest request) {
        final Item item = Item.builder()
            .name(request.getItemName())
            .description(request.getItemDescription())
            .originalPrice(request.getItemOriginalPrice())
            .build();

        Stream.ofNullable(request.getStocks())
            .flatMap(Collection::stream)
            .map(stockMapper::createRequestToEntity)
            .forEach(item::addStock);

        return TimeDeal.builder()
            .timeDealPrice(request.getTimeDealPrice())
            .timeDealStartDate(request.getTimeDealStartDate())
            .timeDealEndDate(request.getTimeDealEndDate())
            .item(item)
            .build();
    }
}


