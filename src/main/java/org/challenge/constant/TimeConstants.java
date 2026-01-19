package org.challenge.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constants used for time validation, formatting, and conversion.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TimeConstants {

    public static final String HH_MM_PATTERN = "\\d{1,2}:\\d{2}";
    public static final int MAX_HOUR = 23;
    public static final int MIN_HOUR = 0;
    public static final int MAX_MINUTE = 59;
    public static final int MIN_MINUTE = 0;

    public static final int UNITS_THRESHOLD = 20;
    public static final int MAX_TIME_NUMBER_SUPPORTED = 60;
    public static final int TENS_INDEX_OFFSET = 2;

    public static final int O_CLOCK_MINUTES = 0;
    public static final int QUARTER_MINUTES = 15;
    public static final int HALF_MINUTES = 30;
    public static final int THREE_QUARTER_MINUTES = 45;
    public static final int MINUTES_PER_HOUR = 60;
    public static final int ROUNDING_INTERVAL = 5;
    public static final int SINGLE_DIGIT_THRESHOLD = 10;

    public static final String O_CLOCK = "o'clock";
    public static final String PAST = "past";
    public static final String TO = "to";
    public static final String QUARTER_PAST = "quarter past";
    public static final String HALF_PAST = "half past";
    public static final String QUARTER_TO = "quarter to";
    public static final String MIDNIGHT = "midnight";
    public static final String NOON = "noon";
    public static final String SINGLE_DIGIT_DELIMITER = "oh";
}
