package com.yearupunited.goldencrunchchicken.screens;

import com.yearupunited.goldencrunchchicken.model.*;
import com.yearupunited.goldencrunchchicken.service.ChickenService;
import com.yearupunited.goldencrunchchicken.service.OrderService;
import com.yearupunited.goldencrunchchicken.util.TextFormatter;

import java.math.BigDecimal;
import java.util.Scanner;

public class CheckoutScreen {

    Scanner myScanner = new Scanner(System.in);

    private OrderService orderService;
    private ChickenService chickenService;

    public CheckoutScreen(OrderService orderService, ChickenService chickenService) {
        this.orderService = orderService;
        this.chickenService = chickenService;
    }

    /// Retrieves all items from order and prints out as summary, then prints calculated total.
    public void displayCheckoutScreen(Order order) {

        System.out.println();

        for (Chicken chicken : order.getChickenItems()) {

            System.out.println(TextFormatter.bold(TextFormatter.cyan(chicken.customizedChicken()
                    + ": $" + chickenService.calculatedChickenPrice(chicken))));

        }

        System.out.println();

        for (Drink drink : order.getDrinks()) {

            System.out.println(TextFormatter.bold(TextFormatter.cyan(drink.getDrinkSize()
                    + " " + drink.getDrinkFlavor() + ": $" + drink.getDrinkPrice())));
        }

        System.out.println();

        for (Sides sides : order.getSides()) {

            System.out.println(TextFormatter.bold(TextFormatter.cyan(sides.getSideType().toString().replace("_", " ")
                    + ": $" + sides.getSidePrice())));
        }

        BigDecimal orderTotal = orderService.calculateOrderPrice(order.getOrderId());
        System.out.println(TextFormatter.gold("\nTOTAL: $" + orderTotal));

        System.out.println(TextFormatter.bold(TextFormatter.gold(
                """
                1. [CONFIRM]
                0. [CANCEL]
                """
        )));

        System.out.print(TextFormatter.bold(TextFormatter.cyan("Selection (1 or 0): ")));
        String userChoice = myScanner.nextLine();

        // Stores receipt and prints out filename of receipt
        if (userChoice.equalsIgnoreCase("1")) {
            Receipt receipt = orderService.checkout(order.getOrderId());
            System.out.println(TextFormatter.bold(TextFormatter.cyan("Receipt saved: " + receipt.getFilename())));
        }
        // Cancels and removes order
        else {
            orderService.cancelOrder(order.getOrderId());
            System.out.println(TextFormatter.bold(TextFormatter.red("Order has been cancelled.")));
        }
    }
}
