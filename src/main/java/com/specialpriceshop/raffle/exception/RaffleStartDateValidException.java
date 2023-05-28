package com.specialpriceshop.raffle.exception;

import com.specialpriceshop.common.error.exception.InvalidValueException;
import com.specialpriceshop.common.response.ErrorCode;

public class RaffleStartDateValidException extends InvalidValueException {


    public RaffleStartDateValidException() {
        super(ErrorCode.RAFFLE_START_DATE_INVALID);
    }


}
