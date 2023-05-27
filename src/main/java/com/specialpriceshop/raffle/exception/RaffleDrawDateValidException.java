package com.specialpriceshop.raffle.exception;

public class RaffleDrawDateValidException extends RuntimeException {

    public RaffleDrawDateValidException(final String message) {
        super(message);
    }

    public RaffleDrawDateValidException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
