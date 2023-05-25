package com.specialpriceshop.domain;

import com.specialpriceshop.exception.TimeDealStartDateValidException;
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
        timeDealStartDateValidate(dealStartDate,dealEndDate);
        this.dealStartDate = dealStartDate;
        this.dealEndDate = dealEndDate;
    }

    private void timeDealStartDateValidate(
        final LocalDateTime dealStartDate,
        final LocalDateTime dealEndDate
    ) {
        if (dealStartDate.isAfter(dealEndDate)) {
            throw new TimeDealStartDateValidException("시작시간과 종료시간이 올바르지 않습니다.");
        }
    }
}
