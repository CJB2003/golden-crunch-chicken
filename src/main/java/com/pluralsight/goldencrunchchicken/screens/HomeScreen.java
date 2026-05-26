package com.pluralsight.goldencrunchchicken.screens;

import com.pluralsight.goldencrunchchicken.util.TextFormatter;

import java.util.Scanner;

public class HomeScreen {

    static Scanner myScanner = new Scanner(System.in);

    static OrderScreen orderScreen = new OrderScreen();

    public static void displayHomeScreen() {
        boolean isHomeRunning = true;

        while (isHomeRunning) {
            System.out.println(
                    TextFormatter.bold(TextFormatter.gold(
                            """
                                    ______________________________________
                                    || Welcome to Golden Crunch Chicken ||
                                    ======================================
                                    || 1) New Order                     ||
                                    || 0) Exit                          ||
                                    ======================================
                                    """
                    ))
            );
            System.out.print(TextFormatter.bold(TextFormatter.cyan("Selection: ")));
            String userChoice = myScanner.nextLine();

            switch (userChoice) {
                case "1" -> orderScreen.displayOrderScreen();
                case "0" -> {
                    System.out.println(TextFormatter.bold(TextFormatter.green("\nThank you for coming to Golden Crunch Chicken. Have a good day!")));
                    System.out.println(TextFormatter.bold(TextFormatter.red("\nExiting menu...")));
                    isHomeRunning = false;
                }
                default -> System.out.println(TextFormatter.bold(TextFormatter.red("\nInvalid choice. Please try again.")));
            }
        }
    }
}
