package com.specialpriceshop.raffle.application.command;

import com.specialpriceshop.raffle.domain.Raffle;
import com.specialpriceshop.raffle.domain.RaffleRepository;
import com.specialpriceshop.raffle.dto.RaffleItemCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RaffleCreateService {

    private final RaffleRepository raffleRepository;

    public Long createRaffleItem(final RaffleItemCreateRequest request) {
        final Raffle saved = raffleRepository.save(request.toEntity());
        return saved.getId();
    }
}
