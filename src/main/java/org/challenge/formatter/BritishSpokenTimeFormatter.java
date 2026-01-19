package org.challenge.formatter;

import org.challenge.converter.AbstractTimeNumberToWordConverter;
import org.challenge.model.Time;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BritishSpokenTimeFormatter implements TimeFormatter {

    private static final int O_CLOCK_MINUTES = 0;
    private static final int QUARTER_MINUTES = 15;
    private static final int HALF_MINUTES = 30;
    private static final int THREE_QUARTER_MINUTES = 45;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int ROUNDING_INTERVAL = 5;
    private static final int SINGLE_DIGIT_THRESHOLD = 10;

    private static final String O_CLOCK = "o'clock";
    private static final String PAST = "past";
    private static final String TO = "to";
    private static final String QUARTER_PAST = "quarter past";
    private static final String HALF_PAST = "half past";
    private static final String QUARTER_TO = "quarter to";
    private static final String MIDNIGHT = "midnight";
    private static final String NOON = "noon";
    private static final String SINGLE_DIGIT_DELIMITER = "oh";

    private final AbstractTimeNumberToWordConverter timeNumberToWordConverter;

    public BritishSpokenTimeFormatter(AbstractTimeNumberToWordConverter timeNumberToWordConverter) {
        this.timeNumberToWordConverter = Objects.requireNonNull(
                timeNumberToWordConverter, "Number converter must not be null");
    }

    @Override
    public String format(Time time) {
        Objects.requireNonNull(time, "Time must not be null");

        if (isMidnight(time)) return MIDNIGHT;
        if (isNoon(time)) return NOON;

        int hour = time.to12HourFormat();
        int minute = time.minute();

        if (requiresDigitalFormat(minute)) {
            return formatDigitalTime(hour, minute);
        }

        return switch (minute) {
            case O_CLOCK_MINUTES -> formatOClock(hour);
            case QUARTER_MINUTES -> formatQuarterPast(hour);
            case HALF_MINUTES -> formatHalfPast(hour);
            case THREE_QUARTER_MINUTES -> formatQuarterTo(time.nextHour12Format());
            default -> isPast(minute)
                    ? formatPast(minute, hour)
                    : formatTo(minutesToNextHour(minute), time.nextHour12Format());
        };
    }

    private boolean isMidnight(Time time) {
        return time.hour() == 0 && time.minute() == 0;
    }

    private boolean isNoon(Time time) {
        return time.hour() == 12 && time.minute() == 0;
    }

    private boolean requiresDigitalFormat(int minute) {
        return minute % ROUNDING_INTERVAL != 0;
    }

    private boolean isPast(int minute) {
        return minute < HALF_MINUTES;
    }

    private int minutesToNextHour(int minute) {
        return MINUTES_PER_HOUR - minute;
    }

    private String formatDigitalTime(int hour, int minute) {
        String hourWord = timeNumberToWordConverter.convert(hour);
        String minuteWord = timeNumberToWordConverter.convert(minute);
        if (minute < SINGLE_DIGIT_THRESHOLD) {
            return buildPhrase(hourWord, SINGLE_DIGIT_DELIMITER, minuteWord);
        }
        return buildPhrase(hourWord, minuteWord);
    }

    private String formatOClock(int hour) {
        return buildPhrase(timeNumberToWordConverter.convert(hour), O_CLOCK);
    }

    private String formatQuarterPast(int hour) {
        return buildPhrase(QUARTER_PAST, timeNumberToWordConverter.convert(hour));
    }

    private String formatHalfPast(int hour) {
        return buildPhrase(HALF_PAST, timeNumberToWordConverter.convert(hour));
    }

    private String formatQuarterTo(int nextHour) {
        return buildPhrase(QUARTER_TO, timeNumberToWordConverter.convert(nextHour));
    }

    private String formatPast(int minute, int hour) {
        return buildPhrase(timeNumberToWordConverter.convert(minute), PAST, timeNumberToWordConverter.convert(hour));
    }

    private String formatTo(int minutesToNextHour, int nextHour) {
        return buildPhrase(timeNumberToWordConverter.convert(minutesToNextHour), TO, timeNumberToWordConverter.convert(nextHour));
    }

    private static String buildPhrase(String... parts) {
        return Stream.of(parts)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining(" "));
    }
}
