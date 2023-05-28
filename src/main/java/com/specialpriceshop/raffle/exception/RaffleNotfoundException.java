package com.specialpriceshop.raffle.exception;

import com.specialpriceshop.common.error.exception.EntityNotFoundException;
import com.specialpriceshop.common.response.ErrorCode;

public class RaffleNotfoundException extends EntityNotFoundException {

    public RaffleNotfoundException(final String target) {
        super(target + " is Not Found", ErrorCode.RAFFLE_NOT_FOUND);
    }
}
