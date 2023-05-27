package com.specialpriceshop.raffle.exception;

public class RaffleNotfoundException extends RuntimeException {
    public RaffleNotfoundException(final String message) {
        super(message);
    }

    public RaffleNotfoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RaffleNotfoundException() {
        super();
    }
}
