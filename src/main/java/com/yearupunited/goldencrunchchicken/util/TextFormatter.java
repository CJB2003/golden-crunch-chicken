package com.yearupunited.goldencrunchchicken.util;

public class TextFormatter {

    /// ASCII colors
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String GOLD = "\u001B[93m";
    public static final String WHITE = "\u001B[97m";
    public static final String CYAN = "\u001B[96m";

    /// Made them static and final so I can pass them through multiple methods. Formats text bold
    private static final String bold = "\u001B[1m";

    //Resets formatting
    private static final String reset = "\u001B[0m";

    public static String bold(String text) {
        return bold + text + reset;
    }

    public static String gold(String text) {
        return GOLD + text + reset;
    }

    public static String red(String text) {
        return RED + text + reset;
    }

    public static String green(String text) {
        return GREEN + text + reset;
    }

    public static String cyan(String text) {
        return CYAN + text + reset;
    }

    public static String white(String text) {
        return WHITE + text + reset;
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static String colorForAmount(double amount) {

        // Formats the text to 2 decimal places for amount
        String formatter = String.format("%.2f", amount);

        /*
        Checks if amount is greater or less than zero, if greater than returns amount green, red if less than
         */
        if (amount > 0) {
            return GREEN + formatter + reset;
        } else {
            return RED + formatter + reset;
        }
    }
}
