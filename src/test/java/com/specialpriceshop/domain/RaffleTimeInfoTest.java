package com.specialpriceshop.domain;

import com.specialpriceshop.exception.RaffleDrawDateValidException;
import com.specialpriceshop.exception.RafflePaymentDueDateValidException;
import com.specialpriceshop.exception.RaffleStartDateValidException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RaffleTimeInfoTest {

    @Test
    @DisplayName("RaffleTimeInfo 생성")
    void raffleTimeInfo_create() {
        LocalDateTime raffleStartDate = LocalDateTime.now();
        LocalDateTime raffleEndDate = LocalDateTime.now().plusDays(1);
        LocalDateTime drawDate = LocalDateTime.now().plusDays(2);
        LocalDateTime paymentDueDate = LocalDateTime.now().plusDays(3);

        Assertions.assertDoesNotThrow(
            () -> new RaffleTimeInfo(raffleStartDate, raffleEndDate, drawDate, paymentDueDate));
    }


    @Test
    @DisplayName("시작시간 후 종료시간이 아닌 경우")
    void raffleStartDateValidate_startAfterEnd_throwsException() {
        LocalDateTime raffleStartDate = LocalDateTime.now().plusDays(1);
        LocalDateTime raffleEndDate = LocalDateTime.now();

        Assertions.assertThrows(RaffleStartDateValidException.class,
            () -> new RaffleTimeInfo(raffleStartDate, raffleEndDate, null, null));
    }

    @Test
    @DisplayName("종료시간 후 당첨시간이 아닌 경우")
    void raffleDrawDateValidate_endAfterDraw_throwsException() {
        LocalDateTime raffleStartDate = LocalDateTime.now();
        LocalDateTime raffleEndDate = LocalDateTime.now().plusDays(1);
        LocalDateTime drawDate = LocalDateTime.now();

        Assertions.assertThrows(RaffleDrawDateValidException.class,
            () -> new RaffleTimeInfo(raffleStartDate, raffleEndDate, drawDate, null));
    }

    @Test
    @DisplayName("당첨시간 후 결제만료 시간이 아닌 경우")
    void rafflePaymentDuDateValidate_drawAfterPayment_throwsException() {
        LocalDateTime raffleStartDate = LocalDateTime.now();
        LocalDateTime raffleEndDate = LocalDateTime.now().plusDays(1);
        LocalDateTime drawDate = LocalDateTime.now().plusDays(2);
        LocalDateTime paymentDueDate = LocalDateTime.now().plusDays(1);

        Assertions.assertThrows(RafflePaymentDueDateValidException.class,
            () -> new RaffleTimeInfo(raffleStartDate, raffleEndDate, drawDate, paymentDueDate));
    }
}