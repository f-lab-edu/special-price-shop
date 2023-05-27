package com.specialpriceshop.application.query;

import com.specialpriceshop.dto.RaffleItemDetailResponse;
import com.specialpriceshop.dto.RaffleItemResponse;
import com.specialpriceshop.exception.RaffleNotfoundException;
import com.specialpriceshop.repository.RaffleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

    public List<RaffleItemResponse> queryRaffleList() {

        return raffleRepository.findAll()
            .stream()
            .map(RaffleItemResponse::of)
            .toList();
    }
}
