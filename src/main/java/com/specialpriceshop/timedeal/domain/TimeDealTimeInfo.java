package com.specialpriceshop.timedeal.domain;

import com.specialpriceshop.timedeal.exception.TimeDealStartDateValidException;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeDealTimeInfo {

    private LocalDateTime dealStartDate;

    private LocalDateTime dealEndDate;

    public TimeDealTimeInfo(
        final LocalDateTime dealStartDate,
        final LocalDateTime dealEndDate
    ) {
        timeDealStartDateValidate(dealStartDate, dealEndDate);
        this.dealStartDate = dealStartDate;
        this.dealEndDate = dealEndDate;
    }

    private void timeDealStartDateValidate(
        final LocalDateTime dealStartDate,
        final LocalDateTime dealEndDate
    ) {
        if (dealStartDate.isAfter(dealEndDate)) {
            throw new TimeDealStartDateValidException();
        }
    }
}
