package com.specialpriceshop.timedeal.exception;

public class TimeDealStartDateValidException extends RuntimeException{


    public TimeDealStartDateValidException(final String message) {
        super(message);
    }

    public TimeDealStartDateValidException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
