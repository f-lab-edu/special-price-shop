package com.specialpriceshop.raffle.application.command;

import com.specialpriceshop.raffle.domain.Raffle;
import com.specialpriceshop.raffle.dto.RaffleItemCreateRequest;
import com.specialpriceshop.raffle.mapper.RaffleMapper;
import com.specialpriceshop.raffle.repository.RaffleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RaffleCreateService {

    private final RaffleRepository raffleRepository;
    private final RaffleMapper raffleMapper;

    public Long createRaffleItem(final RaffleItemCreateRequest request) {
        final Raffle saved = raffleRepository.save(raffleMapper.createRequestToEntity(request));
        return saved.getId();
    }
}
