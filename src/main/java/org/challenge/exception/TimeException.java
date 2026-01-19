package org.challenge.exception;

/**
 * Generic exception for all time-related errors in the application.
 * The specific cause is described by {@link TimeErrorReason}.
 */
public class TimeException extends RuntimeException {

    private final TimeErrorReason reason;

    public TimeException(TimeErrorReason reason, String message) {
        super(message);
        this.reason = reason;
    }

    public TimeErrorReason getReason() {
        return reason;
    }
}
