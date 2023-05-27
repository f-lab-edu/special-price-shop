package com.specialpriceshop.timedeal.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.specialpriceshop.timedeal.exception.TimeDealStartDateValidException;
import com.specialpriceshop.timedeal.domain.TimeDealTimeInfo;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TimeDealTimeInfoTest {

    @Test
    @DisplayName("TimeDealTimeInfo 생성")
    void timeDealTimeInfo_create() {

        LocalDateTime dealStartDate = LocalDateTime.now();
        LocalDateTime dealEndDate = LocalDateTime.now().plusDays(1);

        Assertions.assertDoesNotThrow(() -> new TimeDealTimeInfo(dealStartDate, dealEndDate));
    }

    @Test
    @DisplayName("시작시간 후 종료시간이 아닌 경우")
    void testInvalidTimeDeal() {
        LocalDateTime dealStartDate = LocalDateTime.now().plusDays(1);
        LocalDateTime dealEndDate = LocalDateTime.now();
        assertThrows(TimeDealStartDateValidException.class,
            () -> new TimeDealTimeInfo(dealStartDate, dealEndDate));
    }
}