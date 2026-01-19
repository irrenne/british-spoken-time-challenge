package org.challenge.exception;

/**
 * Thrown when a number cannot be converted to words correctly.
 */
public class NumberToWordConversionException extends RuntimeException {

    public NumberToWordConversionException(String message) {
        super(message);
    }
}
