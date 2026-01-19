package org.challenge.model;

import org.challenge.constant.TimeConstants;
import org.challenge.exception.TimeErrorReason;
import org.challenge.exception.TimeException;

import java.util.Objects;

/**
 * Represents a time value with hour and minute components.
 * Uses 24-hour format and provides conversion to 12-hour format.
 *
 * @param hour   the hour component
 * @param minute the minute component
 */
public record Time(int hour, int minute) {

    /**
     * Constructor with validation for hours and minutes.
     *
     * @param hour   hour in 24-hour format
     * @param minute minute
     * @throws TimeException if hour or minute are out of bounds
     */
    public Time {
        validate(hour, minute);
    }

    /**
     * Creates a {@link Time} object from hour and minute integers.
     *
     * @param hour   hour in 24-hour format
     * @param minute minute
     * @return a {@link Time} object
     * @throws TimeException if hour or minute are invalid
     */
    public static Time of(int hour, int minute) {
        return new Time(hour, minute);
    }

    /**
     * Creates a {@link Time} object by parsing a string in "HH:MM" format.
     *
     * @param input the string to parse
     * @return a {@link Time} object
     * @throws NullPointerException if input is null
     * @throws TimeException        if input format is invalid or values are out of range
     */
    public static Time of(String input) {
        Objects.requireNonNull(input, "Input cannot be null");

        if (input.isBlank()) {
            throw new TimeException(
                    TimeErrorReason.EMPTY_INPUT,
                    "Input cannot be empty or blank"
            );
        }

        if (!input.matches(TimeConstants.HH_MM_PATTERN)) {
            throw new TimeException(
                    TimeErrorReason.INVALID_TIME_FORMAT,
                    "Time must be in HH:MM format"
            );
        }

        String[] parts = input.split(":");
        return new Time(
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1])
        );
    }

    /**
     * Converts the 24-hour format hour to 12-hour format.
     *
     * @return hour in 12-hour format
     */
    public int to12HourFormat() {
        int hour = this.hour % 12;
        return hour == 0 ? 12 : hour;
    }

    /**
     * Returns the next hour in 12-hour format.
     *
     * @return next hour in 12-hour format
     */
    public int nextHour12Format() {
        int currentHour = to12HourFormat();
        return currentHour == 12 ? 1 : currentHour + 1;
    }

    /**
     * Converts the {@link Time} object into a string in "HH:MM" format.
     *
     * @return string representation of the time
     */
    @Override
    public String toString() {
        return String.format("%d:%02d", hour, minute);
    }

    private static void validate(int hour, int minute) {
        if (hour < TimeConstants.MIN_HOUR || hour > TimeConstants.MAX_HOUR) {
            throw new TimeException(
                    TimeErrorReason.INVALID_HOUR_RANGE,
                    String.format("Hour must be between %d and %d, but was %d",
                            TimeConstants.MIN_HOUR, TimeConstants.MAX_HOUR, hour));
        }
        if (minute < TimeConstants.MIN_MINUTE || minute > TimeConstants.MAX_MINUTE) {
            throw new TimeException(
                    TimeErrorReason.INVALID_MINUTE_RANGE,
                    String.format("Minute must be between %d and %d, but was %d",
                            TimeConstants.MIN_MINUTE, TimeConstants.MAX_MINUTE, minute));
        }
    }
}

