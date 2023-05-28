package com.specialpriceshop.timedeal.exception;

import com.specialpriceshop.common.error.exception.InvalidValueException;
import com.specialpriceshop.common.response.ErrorCode;

public class TimeDealStartDateValidException extends InvalidValueException {

    public TimeDealStartDateValidException() {
        super(ErrorCode.TIME_DEAL_START_DATE_INVALID);
    }
}
