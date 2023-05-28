package com.specialpriceshop.raffle.exception;

import com.specialpriceshop.common.error.exception.InvalidValueException;
import com.specialpriceshop.common.response.ErrorCode;

public class RaffleDrawDateValidException extends InvalidValueException {

    public RaffleDrawDateValidException() {
        super(ErrorCode.RAFFLE_DRAW_DATE_INVALID);
    }
}
