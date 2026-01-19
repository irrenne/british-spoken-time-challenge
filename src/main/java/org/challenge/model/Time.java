package org.challenge.model;

import org.challenge.exception.InvalidTimeException;

import java.util.Objects;

/**
 * Represents a time value with hour and minute components.
 * Uses 24-hour format and provides conversion to 12-hour format.
 *
 * @param hour   the hour component
 * @param minute the minute component
 */
public record Time(int hour, int minute) {

    private static final String HH_MM_PATTERN = "\\d{1,2}:\\d{2}";
    private static final int MAX_HOUR = 23;
    private static final int MIN_HOUR = 0;
    private static final int MAX_MINUTE = 59;
    private static final int MIN_MINUTE = 0;

    /**
     * Constructor with validation for hours and minutes.
     *
     * @param hour   hour in 24-hour format
     * @param minute minute
     * @throws InvalidTimeException if hour or minute are out of bounds
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
     * @throws InvalidTimeException if hour or minute are invalid
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
     * @throws InvalidTimeException if input format is invalid or values are out of range
     */
    public static Time of(String input) {
        Objects.requireNonNull(input, "Input cannot be null");

        if (!input.matches(HH_MM_PATTERN)) {
            throw new InvalidTimeException("Time must be in HH:MM format");
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
        if (hour < MIN_HOUR || hour > MAX_HOUR) {
            throw new InvalidTimeException(
                    String.format("Hour must be between %d and %d, but was %d", MIN_HOUR, MAX_HOUR, hour));
        }
        if (minute < MIN_MINUTE || minute > MAX_MINUTE) {
            throw new InvalidTimeException(
                    String.format("Minute must be between %d and %d, but was %d", MIN_MINUTE, MAX_MINUTE, minute));
        }
    }
}
