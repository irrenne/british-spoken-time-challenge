package org.challenge.formatter;

import org.challenge.model.Time;

/**
 * Interface for formatting {@link Time} objects into spoken form.
 */
public interface TimeFormatter {

    /**
     * Formats a {@link Time} object into its spoken form.
     *
     * @param time the time to format
     * @return the spoken form of the time
     */
    String format(Time time);
}
