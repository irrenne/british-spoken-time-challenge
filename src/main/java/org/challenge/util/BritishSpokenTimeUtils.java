package org.challenge.util;

import org.challenge.constant.TimeConstants;
import org.challenge.converter.AbstractTimeNumberToWordConverter;
import org.challenge.model.Time;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility helpers for building British-spoken time phrases from numbers.
 */
public final class BritishSpokenTimeUtils {

    private BritishSpokenTimeUtils() {
    }

    public static boolean isMidnight(Time time) {
        return time.hour() == 0 && time.minute() == 0;
    }

    public static boolean isNoon(Time time) {
        return time.hour() == 12 && time.minute() == 0;
    }

    public static boolean requiresDigitalFormat(int minute) {
        return minute % TimeConstants.ROUNDING_INTERVAL != 0;
    }

    public static boolean isPast(int minute) {
        return minute < TimeConstants.HALF_MINUTES;
    }

    public static int minutesToNextHour(int minute) {
        return TimeConstants.MINUTES_PER_HOUR - minute;
    }

    public static String formatDigitalTime(AbstractTimeNumberToWordConverter converter, int hour, int minute) {
        String hourWord = converter.convert(hour);
        String minuteWord = converter.convert(minute);
        if (minute < TimeConstants.SINGLE_DIGIT_THRESHOLD) {
            return buildPhrase(hourWord, TimeConstants.SINGLE_DIGIT_DELIMITER, minuteWord);
        }
        return buildPhrase(hourWord, minuteWord);
    }

    public static String formatOClock(AbstractTimeNumberToWordConverter converter, int hour) {
        return buildPhrase(converter.convert(hour), TimeConstants.O_CLOCK);
    }

    public static String formatQuarterPast(AbstractTimeNumberToWordConverter converter, int hour) {
        return buildPhrase(TimeConstants.QUARTER_PAST, converter.convert(hour));
    }

    public static String formatHalfPast(AbstractTimeNumberToWordConverter converter, int hour) {
        return buildPhrase(TimeConstants.HALF_PAST, converter.convert(hour));
    }

    public static String formatQuarterTo(AbstractTimeNumberToWordConverter converter, int nextHour) {
        return buildPhrase(TimeConstants.QUARTER_TO, converter.convert(nextHour));
    }

    public static String formatPast(AbstractTimeNumberToWordConverter converter, int minute, int hour) {
        return buildPhrase(converter.convert(minute), TimeConstants.PAST, converter.convert(hour));
    }

    public static String formatTo(AbstractTimeNumberToWordConverter converter, int minutesToNextHour, int nextHour) {
        return buildPhrase(converter.convert(minutesToNextHour), TimeConstants.TO, converter.convert(nextHour));
    }

    public static String buildPhrase(String... parts) {
        return Stream.of(parts)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining(" "));
    }
}
