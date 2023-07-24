package com.specialpriceshop.order.application.command;


import com.specialpriceshop.account.domain.AccountId;
import com.specialpriceshop.order.domain.Order;
import com.specialpriceshop.order.dto.OrderCreateRequest;
import com.specialpriceshop.order.repository.OrderRepository;
import com.specialpriceshop.timedeal.domain.TimeDeal;
import com.specialpriceshop.timedeal.exception.TimeDealNotFoundException;
import com.specialpriceshop.timedeal.exception.TimeDealNotProgressingException;
import com.specialpriceshop.timedeal.repository.TimeDealRepository;
import java.time.LocalDateTime;
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
        final AccountId accountId
    ) {
        final TimeDeal timeDeal = timeDealRepository.findById(timeDealId)
            .orElseThrow(TimeDealNotFoundException::new);

        validate(timeDeal, orderCreateRequest);

        final Order order = orderCreateRequest.toEntity(accountId, timeDeal);

        return orderRepository.save(order).getId();
    }

    private void validate(
        final TimeDeal timeDeal,
        final OrderCreateRequest orderCreateRequest
    ) {

        if (!timeDeal.isAvailable(LocalDateTime.now())) {
            throw new TimeDealNotProgressingException();
        }

        timeDeal.isOrderable(
            orderCreateRequest.getStockId(),
            orderCreateRequest.getQuantity()
        );
    }
}
