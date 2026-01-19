package org.challenge.factory;

import org.challenge.converter.EnglishTimeNumberToWordConverter;
import org.challenge.formatter.BritishSpokenTimeFormatter;
import org.challenge.formatter.TimeFormatter;

public final class TimeFormatterFactory {

    private TimeFormatterFactory() {
    }

    public static TimeFormatter createBritishSpokenTimeFormatter() {
        return new BritishSpokenTimeFormatter(
                new EnglishTimeNumberToWordConverter()
        );
    }
}
