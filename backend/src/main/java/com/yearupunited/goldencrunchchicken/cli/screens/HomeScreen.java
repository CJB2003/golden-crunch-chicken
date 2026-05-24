package com.yearupunited.goldencrunchchicken.cli.screens;

import com.yearupunited.goldencrunchchicken.util.TextFormatter;

import java.util.Scanner;

public class HomeScreen {

    Scanner myScanner = new Scanner(System.in);

    OrderScreen orderScreen = new OrderScreen();

    public void displayHomeScreen() {
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
                                    \n"""
                    ))
            );
            System.out.print(TextFormatter.gold("Selection: "));
            String userChoice = myScanner.nextLine();

            switch (userChoice) {
                case "1" -> orderScreen.displayOrderScreen();
                case "0" -> isHomeRunning = false;
                default -> System.out.println(TextFormatter.red("\nInvalid choice. Please try again."));
            }
        }
    }
}
