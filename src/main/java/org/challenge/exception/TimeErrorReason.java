package org.challenge.exception;

/**
 * Represents the specific reason for a {@link TimeException}.
 */
public enum TimeErrorReason {
    EMPTY_INPUT,
    INVALID_TIME_FORMAT,
    INVALID_HOUR_RANGE,
    INVALID_MINUTE_RANGE,
    UNSUPPORTED_NUMBER
}
