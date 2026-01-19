package org.challenge.exception;

/**
 * Thrown when an invalid time value is encountered.
 */
public class InvalidTimeException extends RuntimeException {

    public InvalidTimeException(String message) {
        super(message);
    }
}
