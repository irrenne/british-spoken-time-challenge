package org.challenge.converter;

import org.challenge.exception.TimeErrorReason;
import org.challenge.exception.TimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test suite for {@link EnglishTimeNumberToWordConverter}.
 */
class EnglishTimeNumberToWordConverterTest {

    private final EnglishTimeNumberToWordConverter converter = new EnglishTimeNumberToWordConverter();

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "0, ''",
            "1, one",
            "5, five",
            "10, ten",
            "12, twelve",
            "19, nineteen"
    })
    void testUnits(int input, String expected) {
        assertEquals(expected, converter.convert(input));
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "20, twenty",
            "30, thirty",
            "40, forty",
            "50, fifty"
    })
    void testTens(int input, String expected) {
        assertEquals(expected, converter.convert(input));
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "21, twenty one",
            "25, twenty five",
            "32, thirty two",
            "43, forty three",
            "59, fifty nine"
    })
    void testCompositeNumbers(int input, String expected) {
        assertEquals(expected, converter.convert(input));
    }

    @Test
    void testInvalidNegative() {
        TimeException exception = assertThrows(TimeException.class, () -> converter.convert(-1),
                "Should throw exception for negative numbers");
        assertEquals(TimeErrorReason.UNSUPPORTED_NUMBER, exception.getReason());
    }

    @Test
    void testInvalidTooHigh() {
        TimeException exception = assertThrows(TimeException.class, () -> converter.convert(100),
                "Should throw exception for numbers > 59");
        assertEquals(TimeErrorReason.UNSUPPORTED_NUMBER, exception.getReason());
    }
}

