package com.specialpriceshop.order.application.command;

import com.specialpriceshop.item.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OptimisticStockManagementStrategy implements StockManagementStrategy{

    private final StockRepository stockRepository;
    @Override
    public void decreaseStock(final Long stockId, final Long quantity) {
        int i = stockRepository.optimisticDecrease(stockId, quantity);
    }
}
