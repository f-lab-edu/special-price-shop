package com.specialpriceshop.timedeal.exception;

public class TimeDealNotFoundException extends RuntimeException{
    public TimeDealNotFoundException(final String message) {
        super(message);
    }

    public TimeDealNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TimeDealNotFoundException() {
        super();
    }
}
