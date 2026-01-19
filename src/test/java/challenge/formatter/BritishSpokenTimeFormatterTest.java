package challenge.formatter;

import org.challenge.converter.EnglishTimeNumberToWordConverter;
import org.challenge.formatter.BritishSpokenTimeFormatter;
import org.challenge.model.Time;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test suite for {@link BritishSpokenTimeFormatter}.
 */
class BritishSpokenTimeFormatterTest {

    private final BritishSpokenTimeFormatter formatter =
            new BritishSpokenTimeFormatter(new EnglishTimeNumberToWordConverter());

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "1,0,one o'clock",
            "2,5,five past two",
            "3,10,ten past three",
            "4,15,quarter past four",
            "5,20,twenty past five",
            "6,25,twenty five past six",
            "6,32,six thirty two",
            "7,30,half past seven",
            "7,35,twenty five to eight",
            "8,40,twenty to nine",
            "9,45,quarter to ten",
            "10,50,ten to eleven",
            "11,55,five to twelve",
            "0,0,midnight",
            "12,0,noon"
    })
    void testStandardExamples(int hour, int minute, String expected) {
        Time time = Time.of(hour, minute);
        assertEquals(expected, formatter.format(time));
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "0,5,five past twelve",
            "12,35,twenty five to one",
            "12,50,ten to one",
            "11,35,twenty five to twelve"
    })
    void testEdgeHours(int hour, int minute, String expected) {
        assertEquals(expected, formatter.format(Time.of(hour, minute)));
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "2,1,two oh one",
            "3,3,three oh three",
            "11,9,eleven oh nine",
            "12,1,twelve oh one",
            "5,4,five oh four"
    })
    void testSingleDigitMinutes(int hour, int minute, String expected) {
        assertEquals(expected, formatter.format(Time.of(hour, minute)));
    }

    @Test
    void testInvalidTimeNull() {
        assertThrows(NullPointerException.class, () -> formatter.format(null),
                "Should throw NullPointerException for null time");
    }

    @Test
    void testConstructorInvalidConverterNull() {
        assertThrows(NullPointerException.class,
                () -> new BritishSpokenTimeFormatter(null),
                "Should throw NullPointerException for null converter");
    }
}
