package org.challenge.converter;

public abstract class AbstractTimeNumberToWordConverter {

    protected abstract String[] getUnits();

    protected abstract String[] getTens();

    protected String delimiter() {
        return " ";
    }

    public abstract String convert(int number);
}
