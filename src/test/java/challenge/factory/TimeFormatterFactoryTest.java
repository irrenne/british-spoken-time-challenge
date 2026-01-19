package challenge.factory;

import org.challenge.factory.TimeFormatterFactory;
import org.challenge.formatter.BritishSpokenTimeFormatter;
import org.challenge.formatter.TimeFormatter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TimeFormatterFactoryTest {

    @Test
    void createBritishSpokenFormatter_shouldReturnNonNull() {
        TimeFormatter formatter = TimeFormatterFactory.createBritishSpokenTimeFormatter();
        assertNotNull(formatter, "Factory should not return null");
    }

    @Test
    void createBritishSpokenFormatter_shouldReturnCorrectType() {
        TimeFormatter formatter = TimeFormatterFactory.createBritishSpokenTimeFormatter();
        assertInstanceOf(BritishSpokenTimeFormatter.class, formatter, "Factory should return a BritishSpokenTimeFormatter");
    }
}
