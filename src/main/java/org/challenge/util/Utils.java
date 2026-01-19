package org.challenge.util;

import org.challenge.converter.EnglishTimeNumberToWordConverter;
import org.challenge.formatter.BritishSpokenTimeFormatter;
import org.challenge.model.Time;

public final class Utils {

    private Utils() {
    }

    private static final Time[] EXAMPLES = {
            new Time(1, 0),
            new Time(2, 5),
            new Time(3, 10),
            new Time(4, 15),
            new Time(5, 20),
            new Time(6, 25),
            new Time(6, 32),
            new Time(7, 30),
            new Time(7, 35),
            new Time(8, 40),
            new Time(9, 45),
            new Time(10, 50),
            new Time(11, 55),
            new Time(0, 0),
            new Time(12, 0)
    };

    public static void showExamples() {
        BritishSpokenTimeFormatter formatter = new BritishSpokenTimeFormatter(
                new EnglishTimeNumberToWordConverter()
        );
        System.out.println("Examples:");
        for (Time time : EXAMPLES) {
            System.out.printf("%s -> %s\n", time, formatter.format(time));
        }
    }
}
