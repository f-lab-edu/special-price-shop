package com.specialpriceshop.timedeal.exception;

import com.specialpriceshop.common.error.exception.BusinessException;
import com.specialpriceshop.common.response.ErrorCode;

public class TimeDealNotProgressingException extends BusinessException {

    public TimeDealNotProgressingException() {
        super(ErrorCode.TIME_DEAL_NOT_PROGRESSING);
    }
}
