package com.specialpriceshop.raffle.application.query;

import com.specialpriceshop.raffle.domain.RaffleRepository;
import com.specialpriceshop.raffle.dto.RaffleItemDetailResponse;
import com.specialpriceshop.raffle.dto.RaffleItemResponse;
import com.specialpriceshop.raffle.exception.RaffleNotfoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RaffleQueryService {

    private final RaffleRepository raffleRepository;

    public RaffleItemDetailResponse queryRaffle(final Long id) {

        return RaffleItemDetailResponse.of(
            raffleRepository.findById(id)
                .orElseThrow(RaffleNotfoundException::new)
        );
    }

    public Page<RaffleItemResponse> queryRaffleList(final PageRequest pageRequest) {

        return raffleRepository.findAllRaffleItemResponses(pageRequest);
    }
}
