package com.specialpriceshop.application.command;

import com.specialpriceshop.domain.Raffle;
import com.specialpriceshop.dto.RaffleItemCreateRequest;
import com.specialpriceshop.repository.RaffleRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RaffleCreateService {

    private final RaffleRepository raffleRepository;

    public Long createRaffleItem(final RaffleItemCreateRequest request) {
        final Raffle raffle = request.toEntity();

        raffleStartDateValidate(raffle.getRaffleStartDate(), raffle.getRaffleEndDate());
        raffleDrawDateValidate(raffle.getRaffleEndDate(), raffle.getDrawDate());
        rafflePaymentDuDateValidate(raffle.getDrawDate(), raffle.getPaymentDueDate());

        final Raffle save = raffleRepository.save(raffle);
        return save.getId();
    }

    private void raffleStartDateValidate(
        final LocalDateTime raffleStartDate,
        final LocalDateTime raffleEndDate
    ) {
        if (raffleStartDate.isAfter(raffleEndDate)) {
            throw new RuntimeException("시작시간과 종료시간이 올바르지 않습니다.");
        }
    }

    private void raffleDrawDateValidate(
        final LocalDateTime raffleEndDate,
        final LocalDateTime drawDate
    ) {
        if (raffleEndDate.isAfter(drawDate)) {
            throw new RuntimeException("종료시간과 당첨시간이 올바르지 않습니다.");
        }
    }


    private void rafflePaymentDuDateValidate(
        final LocalDateTime drawDate,
        final LocalDateTime paymentDueDate
    ) {
        if (drawDate.isAfter(paymentDueDate)) {
            throw new RuntimeException("당첨시간과 결제만료일이 올바르지 않습니다");
        }
    }

}
