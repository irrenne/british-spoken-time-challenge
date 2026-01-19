package org.challenge.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.challenge.exception.TimeException;
import org.challenge.factory.TimeFormatterFactory;
import org.challenge.formatter.TimeFormatter;
import org.challenge.model.Time;

import java.util.Scanner;

/**
 * Utility class responsible for running the interactive interface.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MenuUtils {

    private static final Time[] EXAMPLES = {
            Time.of(1, 0),
            Time.of(2, 5),
            Time.of(3, 10),
            Time.of(4, 15),
            Time.of(5, 20),
            Time.of(6, 25),
            Time.of(6, 32),
            Time.of(7, 30),
            Time.of(7, 35),
            Time.of(8, 40),
            Time.of(9, 45),
            Time.of(10, 50),
            Time.of(11, 55),
            Time.of(0, 0),
            Time.of(12, 0)
    };

    public static void runInteractiveMenu() {
        Scanner scanner = new Scanner(System.in);
        TimeFormatter formatter = TimeFormatterFactory.createBritishSpokenTimeFormatter();

        boolean running = true;

        while (running) {
            System.out.println("\nBritish Spoken Time Program");
            System.out.println("---------------------------");
            System.out.println("1. Enter a time (HH:MM) to see British spoken form");
            System.out.println("2. Show examples");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> enterTime(scanner, formatter);
                case "2" -> showExamples(formatter);
                case "3" -> {
                    System.out.println("Exiting program...");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }
    }

    private static void enterTime(Scanner scanner, TimeFormatter formatter) {
        System.out.println("Enter times in HH:MM format (type 'back' to return to main menu):");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("back")) {
                return;
            }
            try {
                Time time = Time.of(input);
                System.out.println("British spoken time: " + formatter.format(time));
            } catch (TimeException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
    }

    private static void showExamples(TimeFormatter formatter) {
        System.out.println("Examples:");
        for (Time time : EXAMPLES) {
            System.out.printf("%s -> %s\n", time, formatter.format(time));
        }
    }
}

