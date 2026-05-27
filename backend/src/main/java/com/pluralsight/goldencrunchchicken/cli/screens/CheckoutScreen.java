package com.pluralsight.goldencrunchchicken.cli.screens;

import com.pluralsight.goldencrunchchicken.model.*;
import com.pluralsight.goldencrunchchicken.service.ChickenService;
import com.pluralsight.goldencrunchchicken.service.OrderService;
import com.pluralsight.goldencrunchchicken.util.TextFormatter;

import java.math.BigDecimal;
import java.util.Scanner;

public class CheckoutScreen {

    Scanner myScanner = new Scanner(System.in);

    private final OrderService orderService;
    private final ChickenService chickenService;

    public CheckoutScreen(OrderService orderService, ChickenService chickenService) {
        this.orderService = orderService;
        this.chickenService = chickenService;
    }

    /// Retrieves all items from order and prints out as summary, then prints calculated total.
    public void displayCheckoutScreen(Order order) {

        System.out.println();

        for (Chicken chicken : order.getChickenItems()) {

            System.out.println(TextFormatter.bold(TextFormatter.cyan(chicken.customizedChicken()
                    + "\nPrice of Chicken: $" + String.format("%.2f",chickenService.calculatedChickenPrice(chicken)))));

        }

        System.out.println();

        for (Drink drink : order.getDrinks()) {

            System.out.println(TextFormatter.bold(TextFormatter.cyan(drink.getDrinkSize()
                    + " " + drink.getDrinkFlavor() + ": $" + String.format("%.2f", drink.getDrinkPrice()))));
        }

        System.out.println();

        for (Sides sides : order.getSides()) {

            System.out.println(TextFormatter.bold(TextFormatter.cyan(sides.getSideType().toString().replace("_", " ")
                    + ": $" + String.format("%.2f", sides.getSidePrice()))));
        }

        BigDecimal orderTotal = orderService.calculateOrderPrice(order.getOrderId());
        System.out.println(TextFormatter.bold(TextFormatter.gold("\nTOTAL: $" + String.format("%.2f", orderTotal))));

        System.out.println(TextFormatter.bold(TextFormatter.gold(
                """
                1. [CONFIRM]
                0. [CANCEL]
                """
        )));

        System.out.print(TextFormatter.bold(TextFormatter.cyan("Selection (1 or 0): ")));
        String userChoice = myScanner.nextLine();

        // Stores receipt and prints out filename of receipt, added input validation
        if (userChoice.equalsIgnoreCase("1")) {
            Receipt receipt = orderService.checkout(order.getOrderId());

            if (receipt != null) {
                System.out.println(TextFormatter.bold(TextFormatter.green("Receipt saved: " + receipt.getFilename())));
            }
            else {
                System.out.println(TextFormatter.bold(TextFormatter.red("Receipt could not be saved.")));
            }
        }
        // Cancels and removes order
        else {
            orderService.cancelOrder(order.getOrderId());
            System.out.println(TextFormatter.bold(TextFormatter.red("Order has been cancelled.")));
        }
    }
}
