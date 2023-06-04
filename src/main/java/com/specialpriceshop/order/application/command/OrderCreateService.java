package com.specialpriceshop.order.application.command;


import com.specialpriceshop.order.domain.Order;
import com.specialpriceshop.order.domain.OrderStock;
import com.specialpriceshop.order.domain.Payment;
import com.specialpriceshop.order.dto.OrderCreateRequest;
import com.specialpriceshop.order.repository.OrderRepository;
import com.specialpriceshop.timedeal.domain.TimeDeal;
import com.specialpriceshop.timedeal.exception.TimeDealNotFoundException;
import com.specialpriceshop.timedeal.repository.TimeDealRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderCreateService {

    private final TimeDealRepository timeDealRepository;
    private final OrderRepository orderRepository;

    public Long createTimeDealOrder(
        final Long timeDealId,
        final OrderCreateRequest orderCreateRequest,
        final String userId) {
        final TimeDeal timeDeal = timeDealRepository.findById(timeDealId)
            .orElseThrow(TimeDealNotFoundException::new);

        final Order order = orderCreateRequest.toEntity(
            timeDeal.getItem().getId(),
            userId,
            timeDeal.getTimeDealTimeInfo().getDealEndDate()
        );

        final OrderStock orderStock = order.getOrderline().getOrderStock();

        order.createPayment(new Payment(BigDecimal.valueOf(calcAmount(timeDeal, orderStock))));

        return orderRepository.save(order).getId();
    }

    private double calcAmount(
        final TimeDeal timeDeal,
        final OrderStock orderStock
    ) {

        return timeDeal.getItem().getStocks().stream()
            .filter(stock -> stock.getId().equals(orderStock.getStockId()))
            .mapToDouble(
                s -> (s.getAddPrice() + timeDeal.getTimeDealPrice()) * orderStock.getQuantity())
            .sum();
    }
}
