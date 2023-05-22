package com.specialpriceshop.exception;

public class RaffleStartDateValidException extends RuntimeException{


    public RaffleStartDateValidException(final String message) {
        super(message);
    }

    public RaffleStartDateValidException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
