package org.challenge.formatter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.challenge.constant.TimeConstants;
import org.challenge.converter.AbstractTimeNumberToWordConverter;
import org.challenge.model.Time;
import org.challenge.util.BritishSpokenTimeUtils;

import java.util.Objects;

/**
 * Formats {@link Time} objects into British English spoken form.
 */
@RequiredArgsConstructor
public class BritishSpokenTimeFormatter implements TimeFormatter {

    @NonNull
    private final AbstractTimeNumberToWordConverter converter;

    /**
     * Formats the given {@link Time} object into its British spoken time representation.
     *
     * @param time the time to format
     * @return the British spoken form of the time
     * @throws NullPointerException if time is null
     */
    @Override
    public String format(Time time) {
        Objects.requireNonNull(time, "Time must not be null");

        if (BritishSpokenTimeUtils.isMidnight(time)) return TimeConstants.MIDNIGHT;
        if (BritishSpokenTimeUtils.isNoon(time)) return TimeConstants.NOON;

        int hour = time.to12HourFormat();
        int minute = time.minute();

        if (BritishSpokenTimeUtils.requiresDigitalFormat(minute)) {
            return BritishSpokenTimeUtils.formatDigitalTime(converter, hour, minute);
        }

        return switch (minute) {
            case TimeConstants.O_CLOCK_MINUTES -> BritishSpokenTimeUtils.formatOClock(converter, hour);
            case TimeConstants.QUARTER_MINUTES -> BritishSpokenTimeUtils.formatQuarterPast(converter, hour);
            case TimeConstants.HALF_MINUTES -> BritishSpokenTimeUtils.formatHalfPast(converter, hour);
            case TimeConstants.THREE_QUARTER_MINUTES ->
                    BritishSpokenTimeUtils.formatQuarterTo(converter, time.nextHour12Format());
            default -> BritishSpokenTimeUtils.isPast(minute)
                    ? BritishSpokenTimeUtils.formatPast(converter, minute, hour)
                    : BritishSpokenTimeUtils.formatTo(converter,
                    BritishSpokenTimeUtils.minutesToNextHour(minute), time.nextHour12Format());
        };
    }
}
