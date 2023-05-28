package com.specialpriceshop.raffle.exception;

import com.specialpriceshop.common.error.exception.InvalidValueException;
import com.specialpriceshop.common.response.ErrorCode;

public class RafflePaymentDueDateValidException extends InvalidValueException {

    public RafflePaymentDueDateValidException() {
        super(ErrorCode.RAFFLE_PAYMENT_DUE_DATE_INVALID);
    }
}
