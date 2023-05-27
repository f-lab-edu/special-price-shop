package com.specialpriceshop.timedeal.application.query;

import com.specialpriceshop.timedeal.domain.TimeDealRepository;
import com.specialpriceshop.timedeal.dto.TimeDealItemDetailResponse;
import com.specialpriceshop.timedeal.dto.TimeDealItemResponse;
import com.specialpriceshop.timedeal.exception.TimeDealNotFoundException;
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
