package com.specialpriceshop.raffle.domain;

import com.specialpriceshop.raffle.exception.RaffleDrawDateValidException;
import com.specialpriceshop.raffle.exception.RafflePaymentDueDateValidException;
import com.specialpriceshop.raffle.exception.RaffleStartDateValidException;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RaffleTimeInfo {

    private LocalDateTime raffleStartDate;

    private LocalDateTime raffleEndDate;

    private LocalDateTime drawDate;

    private LocalDateTime paymentDueDate;

    public RaffleTimeInfo(
        final LocalDateTime raffleStartDate,
        final LocalDateTime raffleEndDate,
        final LocalDateTime drawDate,
        final LocalDateTime paymentDueDate
    ) {
        raffleStartDateValidate(raffleStartDate, raffleEndDate);
        raffleDrawDateValidate(raffleEndDate, drawDate);
        rafflePaymentDuDateValidate(drawDate, paymentDueDate);
        this.raffleStartDate = raffleStartDate;
        this.raffleEndDate = raffleEndDate;
        this.drawDate = drawDate;
        this.paymentDueDate = paymentDueDate;
    }

    private void raffleStartDateValidate(
        final LocalDateTime raffleStartDate,
        final LocalDateTime raffleEndDate
    ) {
        if (raffleStartDate.isAfter(raffleEndDate)) {
            throw new RaffleStartDateValidException();
        }
    }

    private void raffleDrawDateValidate(
        final LocalDateTime raffleEndDate,
        final LocalDateTime drawDate
    ) {
        if (raffleEndDate.isAfter(drawDate)) {
            throw new RaffleDrawDateValidException();
        }
    }

    private void rafflePaymentDuDateValidate(
        final LocalDateTime drawDate,
        final LocalDateTime paymentDueDate
    ) {
        if (drawDate.isAfter(paymentDueDate)) {
            throw new RafflePaymentDueDateValidException();
        }
    }

}
