package challenge.util;

import org.challenge.converter.EnglishTimeNumberToWordConverter;
import org.challenge.model.Time;
import org.challenge.util.BritishSpokenTimeUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test suite for {@link BritishSpokenTimeUtils}.
 */
class BritishSpokenTimeUtilsTest {

    private final EnglishTimeNumberToWordConverter converter = new EnglishTimeNumberToWordConverter();

    @Test
    void testIsMidnight() {
        assertTrue(BritishSpokenTimeUtils.isMidnight(Time.of(0, 0)));
        assertFalse(BritishSpokenTimeUtils.isMidnight(Time.of(0, 1)));
        assertFalse(BritishSpokenTimeUtils.isMidnight(Time.of(1, 0)));
    }

    @Test
    void testIsNoon() {
        assertTrue(BritishSpokenTimeUtils.isNoon(Time.of(12, 0)));
        assertFalse(BritishSpokenTimeUtils.isNoon(Time.of(12, 1)));
        assertFalse(BritishSpokenTimeUtils.isNoon(Time.of(0, 0)));
    }

    @ParameterizedTest
    @CsvSource({
            "0,false",
            "5,false",
            "10,false",
            "15,false",
            "30,false",
            "59,true",
            "1,true",
            "32,true"
    })
    void testRequiresDigitalFormatDependsOnRoundingInterval(int minute, boolean expected) {
        assertEquals(expected, BritishSpokenTimeUtils.requiresDigitalFormat(minute));
    }

    @ParameterizedTest
    @CsvSource({
            "0,true",
            "29,true",
            "30,false",
            "59,false"
    })
    void testIsPastIsTrueBeforeHalfPast(int minute, boolean expected) {
        assertEquals(expected, BritishSpokenTimeUtils.isPast(minute));
    }

    @ParameterizedTest
    @CsvSource({
            "0,60",
            "1,59",
            "15,45",
            "30,30",
            "59,1"
    })
    void testMinutesToNextHourCalculatesCorrectly(int minute, int expected) {
        assertEquals(expected, BritishSpokenTimeUtils.minutesToNextHour(minute));
    }

    @Test
    void testBuildPhraseShouldIgnoreNullBlankAndTrim() {
        assertEquals("one two three", BritishSpokenTimeUtils.buildPhrase(" one ", "", null, "two", "   ", "three"));
    }

    @Test
    void testFormatDigitalTimeAddsOhForSingleDigitMinutes() {
        assertEquals("two oh one", BritishSpokenTimeUtils.formatDigitalTime(converter, 2, 1));
        assertEquals("eleven oh nine", BritishSpokenTimeUtils.formatDigitalTime(converter, 11, 9));
        assertEquals("six thirty two", BritishSpokenTimeUtils.formatDigitalTime(converter, 6, 32));
    }
}
