package util;

import java.util.Scanner;

/**
 * InputHandler class for handling user input from console
 */
public class InputHandler {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Read a string input from user
     */
    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Read an integer input from user
     */
    public static int readInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    /**
     * Read a double input from user
     */
    public static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    /**
     * Display a message
     */
    public static void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Display an error message
     */
    public static void displayError(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Display a success message
     */
    public static void displaySuccess(String message) {
        System.out.println("SUCCESS: " + message);
    }

    /**
     * Display a separator line
     */
    public static void displaySeparator() {
        System.out.println("=============================================================");
    }

    /**
     * Display a menu header
     */
    public static void displayHeader(String title) {
        displaySeparator();
        System.out.println("\t\t\t" + title);
        displaySeparator();
    }

    /**
     * Pause and wait for user to press Enter
     */
    public static void pressEnter() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    /**
     * Close the scanner
     */
    public static void closeScanner() {
        scanner.close();
    }
}
