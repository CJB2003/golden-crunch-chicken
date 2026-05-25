package com.yearupunited.goldencrunchchicken.cli.screens;

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

        for (Chicken chicken : order.getChickenItems()) {

            System.out.println(TextFormatter.cyan(chicken.customizedChicken() + ": $" + chickenService.calculatedChickenPrice(chicken)));

        }

        for (Drink drink : order.getDrinks()) {

            System.out.println(TextFormatter.cyan("\n" + drink.getDrinkSize()
                    + " " + drink.getDrinkFlavor() + ": $" + drink.getDrinkPrice()));
        }

        for (Sides sides : order.getSides()) {

            System.out.println(TextFormatter.cyan("\n" + sides.getSideType() + ": $" + sides.getSidePrice()));
        }

        BigDecimal orderTotal = orderService.calculateOrderPrice(order.getOrderId());
        System.out.println(TextFormatter.gold("TOTAL: $" + orderTotal));

        System.out.println(TextFormatter.bold(TextFormatter.gold(
                """
                1. [CONFIRM]
                0. [CANCEL]
                """
        )));

        System.out.print(TextFormatter.cyan("Selection (1 or 0): "));
        String userChoice = myScanner.nextLine();

        // Stores receipt and prints out filename of receipt
        if (userChoice.equalsIgnoreCase("1")) {
            Receipt receipt = orderService.checkout(order.getOrderId());
            System.out.println(TextFormatter.cyan("Receipt saved: " + receipt.getFilename()));
        }
        // Cancels and removes order
        else {
            orderService.cancelOrder(order.getOrderId());
            System.out.println(TextFormatter.red("Order has been cancelled."));
        }
    }
}
