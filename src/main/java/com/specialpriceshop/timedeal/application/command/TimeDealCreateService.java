package com.specialpriceshop.timedeal.application.command;

import com.specialpriceshop.timedeal.domain.TimeDeal;
import com.specialpriceshop.timedeal.dto.TimeDealItemCreateRequest;
import com.specialpriceshop.timedeal.mapper.TimeDealMapper;
import com.specialpriceshop.timedeal.repository.TimeDealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeDealCreateService {

    private final TimeDealRepository timeDealRepository;
    private final TimeDealMapper timeDealMapper;

    public Long createTimeDealItem(final TimeDealItemCreateRequest request) {
        final TimeDeal saved = timeDealRepository.save(
            timeDealMapper.createRequestToEntity(request)
        );
        
        return saved.getId();
    }
}
