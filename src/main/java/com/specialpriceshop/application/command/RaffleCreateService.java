package com.specialpriceshop.application.command;

import com.specialpriceshop.domain.Raffle;
import com.specialpriceshop.dto.RaffleItemCreateRequest;
import com.specialpriceshop.repository.RaffleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RaffleCreateService {

    private final RaffleRepository raffleRepository;

    public Long createRaffleItem(final RaffleItemCreateRequest request) {
        final Raffle save = raffleRepository.save(request.toEntity());
        return save.getId();
    }
}
