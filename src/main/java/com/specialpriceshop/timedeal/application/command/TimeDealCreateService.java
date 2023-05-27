package com.specialpriceshop.timedeal.application.command;

import com.specialpriceshop.timedeal.domain.TimeDeal;
import com.specialpriceshop.timedeal.domain.TimeDealRepository;
import com.specialpriceshop.timedeal.dto.TimeDealItemCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeDealCreateService {

    private final TimeDealRepository timeDealRepository;

    public Long createTimeDealItem(final TimeDealItemCreateRequest request) {
        final TimeDeal saved = timeDealRepository.save(request.toEntity());
        return saved.getId();
    }
}
