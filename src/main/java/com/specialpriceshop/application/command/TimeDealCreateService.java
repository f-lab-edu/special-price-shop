package com.specialpriceshop.application.command;

import com.specialpriceshop.domain.TimeDeal;
import com.specialpriceshop.dto.TimeDealItemCreateRequest;
import com.specialpriceshop.repository.TimeDealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeDealCreateService {

    private final TimeDealRepository timeDealRepository;

    public Long createTimeDealItem(final TimeDealItemCreateRequest request) {
        final TimeDeal save = timeDealRepository.save(request.toEntity());
        return save.getId();
    }
}
