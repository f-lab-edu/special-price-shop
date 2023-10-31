package com.specialpriceshop.item.application.command;

import com.specialpriceshop.item.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockDecreaseService {

    private final StockRepository stockRepository;

}
