package org.challenge.model;

import org.challenge.exception.InvalidTimeException;

public record Time(int hour, int minute) {

    private static final int MAX_HOUR = 23;
    private static final int MIN_HOUR = 0;
    private static final int MAX_MINUTE = 59;
    private static final int MIN_MINUTE = 0;

    public Time {
        validate(hour, minute);
    }

    public int to12HourFormat() {
        int hour = this.hour % 12;
        return hour == 0 ? 12 : hour;
    }

    public int nextHour12Format() {
        int currentHour = to12HourFormat();
        return currentHour == 12 ? 1 : currentHour + 1;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
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
