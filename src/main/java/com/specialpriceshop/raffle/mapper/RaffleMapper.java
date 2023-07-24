package com.specialpriceshop.raffle.mapper;

import com.specialpriceshop.item.domain.Item;
import com.specialpriceshop.item.mapper.StockMapper;
import com.specialpriceshop.raffle.domain.Raffle;
import com.specialpriceshop.raffle.dto.RaffleItemCreateRequest;
import java.util.Collection;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RaffleMapper {

    private final StockMapper stockMapper;

    public Raffle createRequestToEntity(RaffleItemCreateRequest request) {

        final Item item = Item.builder()
            .name(request.getItemName())
            .description(request.getItemDescription())
            .originalPrice(request.getItemOriginalPrice())
            .build();

        Stream.ofNullable(request.getStocks())
            .flatMap(Collection::stream)
            .map(stockMapper::createRequestToEntity)
            .forEach(item::addStock);

        return Raffle.builder()
            .rafflePrice(request.getRafflePrice())
            .raffleStartDate(request.getRaffleStartDate())
            .raffleEndDate(request.getRaffleEndDate())
            .drawDate(request.getDrawDate())
            .paymentDueDate(request.getPaymentDueDate())
            .item(item)
            .build();
    }
}
