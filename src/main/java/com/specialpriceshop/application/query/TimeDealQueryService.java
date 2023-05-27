package com.specialpriceshop.application.query;

import com.specialpriceshop.dto.TimeDealItemDetailResponse;
import com.specialpriceshop.dto.TimeDealItemResponse;
import com.specialpriceshop.exception.TimeDealNotFoundException;
import com.specialpriceshop.repository.TimeDealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TimeDealQueryService {

    private final TimeDealRepository timeDealRepository;

    public TimeDealItemDetailResponse queryTimeDeal(final Long timeDealId) {

        return TimeDealItemDetailResponse.of(
            timeDealRepository.findById(timeDealId)
                .orElseThrow(TimeDealNotFoundException::new));
    }

    public Page<TimeDealItemResponse> queryTimeDealList(final PageRequest pageRequest) {
        return timeDealRepository.findAllTimeDealItemResponses(pageRequest);
    }
}
