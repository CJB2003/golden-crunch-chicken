package com.pluralsight.goldencrunchchicken.util;

public class TextFormatter {

    /// ASCII colors
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String GOLD = "\u001B[93m";
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

}
