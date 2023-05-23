package com.specialpriceshop.exception;

public class RafflePaymentDueDateValidException extends RuntimeException {

    public RafflePaymentDueDateValidException(final String message) {
        super(message);
    }

    public RafflePaymentDueDateValidException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
