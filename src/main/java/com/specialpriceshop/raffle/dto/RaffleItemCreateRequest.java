package com.specialpriceshop.raffle.dto;

import com.specialpriceshop.item.domain.Item;
import com.specialpriceshop.item.dto.StockCreateRequest;
import com.specialpriceshop.raffle.domain.Raffle;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RaffleItemCreateRequest {

    @NotEmpty
    private String itemName;

    @NotEmpty
    private String itemDescription;

    private BigDecimal itemOriginalPrice;

    private BigDecimal rafflePrice;

    @NotNull
    @FutureOrPresent
    private LocalDateTime raffleStartDate;

    @NotNull
    @Future
    private LocalDateTime raffleEndDate;

    @NotNull
    @Future
    private LocalDateTime drawDate;

    @NotNull
    @Future
    private LocalDateTime paymentDueDate;

    @Valid
    private List<StockCreateRequest> stocks = new ArrayList<>();

    public RaffleItemCreateRequest(
        final String itemName,
        final String itemDescription,
        final BigDecimal itemOriginalPrice,
        final BigDecimal rafflePrice,
        final LocalDateTime raffleStartDate,
        final LocalDateTime raffleEndDate,
        final LocalDateTime drawDate,
        final LocalDateTime paymentDueDate,
        final List<StockCreateRequest> stocks
    ) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemOriginalPrice = itemOriginalPrice;
        this.rafflePrice = rafflePrice;
        this.raffleStartDate = raffleStartDate;
        this.raffleEndDate = raffleEndDate;
        this.drawDate = drawDate;
        this.paymentDueDate = paymentDueDate;
        this.stocks = stocks;
    }

    public Raffle toEntity() {
        final Item item = Item.builder()
            .name(itemName)
            .description(itemDescription)
            .originalPrice(itemOriginalPrice)
            .build();

        Stream.ofNullable(stocks)
            .flatMap(Collection::stream)
            .map(StockCreateRequest::toEntity)
            .forEach(item::addStock);

        return Raffle.builder()
            .rafflePrice(rafflePrice)
            .raffleStartDate(raffleStartDate)
            .raffleEndDate(raffleEndDate)
            .drawDate(drawDate)
            .paymentDueDate(paymentDueDate)
            .item(item)
            .build();
    }

}
