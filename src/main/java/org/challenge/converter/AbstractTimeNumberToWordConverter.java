package org.challenge.converter;

/**
 * Abstract base class for converting numbers to their word representations.
 */
public abstract class AbstractTimeNumberToWordConverter {

    /**
     * Returns array of unit words.
     *
     * @return an array of unit words
     */
    protected abstract String[] getUnits();

    /**
     * Returns array of tens words.
     *
     * @return an array of tens words
     */
    protected abstract String[] getTens();

    /**
     * Returns delimiter string between tens and units.
     * Default is a single space.
     *
     * @return the delimiter string
     */
    protected String delimiter() {
        return " ";
    }

    /**
     * Converts number to word representation.
     *
     * @param number the number to convert
     * @return the word representation of the number
     */
    public abstract String convert(int number);
}
