package org.challenge.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.challenge.converter.EnglishTimeNumberToWordConverter;
import org.challenge.formatter.BritishSpokenTimeFormatter;
import org.challenge.formatter.TimeFormatter;

/**
 * Simple Factory for creating {@link TimeFormatter} instances.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TimeFormatterFactory {

    /**
     * Creates a British spoken time formatter.
     *
     * @return a {@link TimeFormatter} for British spoken time
     */
    public static TimeFormatter createBritishSpokenTimeFormatter() {
        return new BritishSpokenTimeFormatter(
                new EnglishTimeNumberToWordConverter()
        );
    }
}
