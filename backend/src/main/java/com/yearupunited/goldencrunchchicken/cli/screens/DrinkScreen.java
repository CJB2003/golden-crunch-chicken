package com.yearupunited.goldencrunchchicken.cli.screens;

import com.yearupunited.goldencrunchchicken.model.Drink;
import com.yearupunited.goldencrunchchicken.model.Order;
import com.yearupunited.goldencrunchchicken.model.enums.DrinkSize;
import com.yearupunited.goldencrunchchicken.service.OrderService;
import com.yearupunited.goldencrunchchicken.util.TextFormatter;

import java.math.BigDecimal;
import java.util.Scanner;

public class DrinkScreen {

    Scanner myScanner = new Scanner(System.in);

    private OrderService orderService;

    public DrinkScreen(OrderService orderService) {
        this.orderService = orderService;
    }

    public Order displayDrinkScreen(Order drinkOrder) {

        System.out.println(TextFormatter.bold(TextFormatter.gold(
                """
                \n
                ----------------------------
                ||       DRINK MENU       ||
                ||------------------------||
                ||  SMALL  MEDIUM  LARGE  ||
                ||  $2.00  $2.50   $3.00  ||
                ||------------------------||
                || 1) BOBA TEA            ||
                || 2) ICED TEA            ||
                || 3) BANANA MILK         ||
                || 4) COKE                ||
                || 5) SPRITE              ||
                || 6) MILKIS              ||
                || 7) DR. PEPPER          ||
                || 8) LEMONADE            ||
                || 9) WATER               ||
                ||________________________||
                """)
        ));
        boolean isChoosingDrink = true;

        while(isChoosingDrink) {

            Drink drink = new Drink();

            System.out.print(TextFormatter.gold("Select a flavor (1-9): "));
            String userChoice = myScanner.nextLine().trim();

            // Set drink flavor based on user choice
            switch (userChoice) {
                case "1" -> drink.setDrinkFlavor("BOBA TEA");
                case "2" -> drink.setDrinkFlavor("ICED TEA");
                case "3" -> drink.setDrinkFlavor("BANANA MILK");
                case "4" -> drink.setDrinkFlavor("COKE");
                case "5" -> drink.setDrinkFlavor("SPRITE");
                case "6" -> drink.setDrinkFlavor("MILKIS");
                case "7" -> drink.setDrinkFlavor("DR. PEPPER");
                case "8" -> drink.setDrinkFlavor("LEMONADE");
                case "9" -> drink.setDrinkFlavor("WATER");
                default -> System.out.println(TextFormatter.red("We don't offer that drink. Please select one of the choices above."));
            }

            System.out.print(TextFormatter.gold("Select a size (SMALL, MEDIUM, OR LARGE): "));
            String userSize = myScanner.nextLine().toUpperCase().trim();

            // Sets size of drink as well as price
            switch (userSize) {
                case "SMALL" -> {
                    drink.setDrinkSize(DrinkSize.SMALL);
                    drink.setDrinkPrice(BigDecimal.valueOf(2.00));
                }
                case "MEDIUM" -> {
                    drink.setDrinkSize(DrinkSize.MEDIUM);
                    drink.setDrinkPrice(BigDecimal.valueOf(2.50));
                }
                case "LARGE" -> {
                    drink.setDrinkSize(DrinkSize.LARGE);
                    drink.setDrinkPrice(BigDecimal.valueOf(3.00));
                }
                default -> System.out.println(TextFormatter.red("We don't offer that size. Please try again."));
            }
            // Asks user is they want to add another drink
            System.out.print("Would you like to add another drink (Y/N): ");
            String userAddOrNo = myScanner.nextLine();

            if (!userAddOrNo.equalsIgnoreCase("y")) {
                isChoosingDrink = false;
            }
            drinkOrder = orderService.addDrinkToOrder(drinkOrder.getOrderId(), drink);
        }
        return drinkOrder;
    }
}
