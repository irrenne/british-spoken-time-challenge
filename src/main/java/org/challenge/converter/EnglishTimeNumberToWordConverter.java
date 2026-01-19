package org.challenge.converter;

import org.challenge.constant.TimeConstants;
import org.challenge.exception.TimeErrorReason;
import org.challenge.exception.TimeException;

/**
 * Converts numbers to English word representations for British English time expressions.
 */
public final class EnglishTimeNumberToWordConverter extends AbstractTimeNumberToWordConverter {

    private static final String[] UNITS = {
            "", "one", "two", "three", "four", "five", "six",
            "seven", "eight", "nine", "ten", "eleven", "twelve",
            "thirteen", "fourteen", "fifteen", "sixteen",
            "seventeen", "eighteen", "nineteen"
    };

    private static final String[] TENS = {
            "twenty", "thirty", "forty", "fifty"
    };

    @Override
    protected String[] getUnits() {
        return UNITS;
    }

    @Override
    protected String[] getTens() {
        return TENS;
    }

    /**
     * Converts number 0-59 to word representation.
     *
     * @param number the number to convert
     * @return the word representation
     * @throws TimeException if number is outside supported range
     */
    @Override
    public String convert(int number) {
        if (number < 0 || number >= TimeConstants.MAX_TIME_NUMBER_SUPPORTED) {
            throw new TimeException(
                    TimeErrorReason.UNSUPPORTED_NUMBER,
                    String.format("Unsupported number: %d. Supported range is 0-59", number));
        }

        if (number < TimeConstants.UNITS_THRESHOLD) {
            return UNITS[number];
        }

        int tensIndex = number / 10;
        int unitsIndex = number % 10;

        String tensWord = TENS[tensIndex - TimeConstants.TENS_INDEX_OFFSET];

        if (unitsIndex == 0) {
            return tensWord;
        }

        return tensWord + delimiter() + UNITS[unitsIndex];
    }
}

