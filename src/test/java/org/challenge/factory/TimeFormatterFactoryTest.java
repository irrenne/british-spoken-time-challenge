package org.challenge.factory;

import org.challenge.formatter.BritishSpokenTimeFormatter;
import org.challenge.formatter.TimeFormatter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test suite for {@link TimeFormatterFactory}.
 */
class TimeFormatterFactoryTest {

    @Test
    void testCreateBritishSpokenFormatterShouldReturnNonNull() {
        TimeFormatter formatter = TimeFormatterFactory.createBritishSpokenTimeFormatter();
        assertNotNull(formatter, "Factory should not return null");
    }

    @Test
    void testCreateBritishSpokenFormatterShouldReturnCorrectType() {
        TimeFormatter formatter = TimeFormatterFactory.createBritishSpokenTimeFormatter();
        assertInstanceOf(BritishSpokenTimeFormatter.class, formatter, "Factory should return a BritishSpokenTimeFormatter");
    }
}
