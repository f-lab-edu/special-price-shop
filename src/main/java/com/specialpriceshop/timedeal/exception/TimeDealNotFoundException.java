package com.specialpriceshop.timedeal.exception;

import com.specialpriceshop.common.error.exception.EntityNotFoundException;
import com.specialpriceshop.common.response.ErrorCode;

public class TimeDealNotFoundException extends EntityNotFoundException {

    public TimeDealNotFoundException(final String target) {
        super(target + " is Not Found", ErrorCode.TIME_DEAL_NOT_FOUND);
    }

    public TimeDealNotFoundException() {
        super(ErrorCode.TIME_DEAL_NOT_FOUND);
    }
}
