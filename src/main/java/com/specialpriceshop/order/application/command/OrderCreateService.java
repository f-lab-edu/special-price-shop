package com.specialpriceshop.order.application.command;


import com.specialpriceshop.order.domain.Order;
import com.specialpriceshop.order.dto.OrderCreateRequest;
import com.specialpriceshop.order.repository.OrderRepository;
import com.specialpriceshop.timedeal.domain.TimeDeal;
import com.specialpriceshop.timedeal.exception.TimeDealNotFoundException;
import com.specialpriceshop.timedeal.repository.TimeDealRepository;
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
        final String userId
    ) {
        final TimeDeal timeDeal = timeDealRepository.findById(timeDealId)
            .orElseThrow(TimeDealNotFoundException::new);

        final Order order = orderCreateRequest.toEntity(userId, timeDeal);

        return orderRepository.save(order).getId();
    }
}