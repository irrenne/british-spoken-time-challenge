package challenge.model;

import org.challenge.exception.TimeErrorReason;
import org.challenge.exception.TimeException;
import org.challenge.model.Time;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test suite for {@link Time}.
 */
class TimeTest {

    @ParameterizedTest(name = "Hour {0}, Minute {1}")
    @CsvSource({
            "0,0",
            "12,30",
            "23,59",
            "0,59",
            "23,0",
            "12,0"
    })
    void testValidTimeCreation(int hour, int minute) {
        Time time = Time.of(hour, minute);
        assertEquals(hour, time.hour());
        assertEquals(minute, time.minute());
    }

    @ParameterizedTest
    @CsvSource({
            "00:00, 0, 0",
            "01:05, 1, 5",
            "12:30, 12, 30",
            "23:59, 23, 59",
            "7:05, 7, 5"
    })
    void shouldCreateTimeFromValidString(String input, int expectedHour, int expectedMinute) {
        Time time = Time.of(input);
        assertEquals(expectedHour, time.hour());
        assertEquals(expectedMinute, time.minute());
    }

    @ParameterizedTest(name = "{0}:00 -> {1}")
    @CsvSource({
            "0,12",
            "1,1",
            "11,11",
            "12,12",
            "13,1",
            "23,11"
    })
    void testTo12HourFormatConversion(int hour24, int expected12) {
        assertEquals(expected12, Time.of(hour24, 0).to12HourFormat());
    }

    @ParameterizedTest(name = "{0}:00 -> {1}")
    @CsvSource({
            "0,1",
            "1,2",
            "11,12",
            "12,1",
            "13,2",
            "23,12"
    })
    void testNextHour12Format(int hour24, int expectedNextHour) {
        assertEquals(expectedNextHour, Time.of(hour24, 0).nextHour12Format());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "99:10",
            "12:60",
            "123",
            "12:",
            ":30",
            "12:3",
            "12:300",
            "aa:bb",
            "12-30"
    })
    void shouldRejectInvalidStringInput(String input) {
        assertThrows(TimeException.class, () -> Time.of(input));
    }

    @Test
    void testInvalidHourTooLow() {
        TimeException exception = assertThrows(TimeException.class, () -> Time.of(-1, 0),
                "Should throw TimeException for hour < 0");
        assertEquals(TimeErrorReason.INVALID_HOUR_RANGE, exception.getReason());
    }

    @Test
    void testInvalidHourTooHigh() {
        TimeException exception = assertThrows(TimeException.class, () -> Time.of(24, 0),
                "Should throw TimeException for hour > 23");
        assertEquals(TimeErrorReason.INVALID_HOUR_RANGE, exception.getReason());
    }

    @Test
    void testInvalidMinuteTooLow() {
        TimeException exception = assertThrows(TimeException.class, () -> Time.of(12, -1),
                "Should throw TimeException for minute < 0");
        assertEquals(TimeErrorReason.INVALID_MINUTE_RANGE, exception.getReason());
    }

    @Test
    void testInvalidMinuteTooHigh() {
        TimeException exception = assertThrows(TimeException.class, () -> Time.of(12, 60),
                "Should throw TimeException for minute > 59");
        assertEquals(TimeErrorReason.INVALID_MINUTE_RANGE, exception.getReason());
    }
}

