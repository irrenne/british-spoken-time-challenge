package org.challenge.converter;

public final class EnglishTimeNumberToWordConverter extends AbstractTimeNumberToWordConverter {

    private static final int UNITS_THRESHOLD = 20;
    private static final int MAX_TIME_NUMBER_SUPPORTED = 60;
    private static final int TENS_INDEX_OFFSET = 2;

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

    @Override
    public String convert(int number) {
        if (number < 0 || number >= MAX_TIME_NUMBER_SUPPORTED) {
            throw new IllegalArgumentException(
                    String.format("Unsupported number: %d. Supported range is 0-59", number));
        }

        if (number < UNITS_THRESHOLD) {
            return UNITS[number];
        }

        int tensIndex = number / 10;
        int unitsIndex = number % 10;

        String tensWord = TENS[tensIndex - TENS_INDEX_OFFSET];

        if (unitsIndex == 0) {
            return tensWord;
        }

        return tensWord + delimiter() + UNITS[unitsIndex];
    }
}
