package com.pluralsight.goldencrunchchicken.screens;

import com.pluralsight.goldencrunchchicken.model.Order;
import com.pluralsight.goldencrunchchicken.service.ChickenService;
import com.pluralsight.goldencrunchchicken.service.OrderService;
import com.pluralsight.goldencrunchchicken.util.TextFormatter;

import java.util.Scanner;

public class OrderScreen {

    private final ChickenService chickenService = new ChickenService();
    private final OrderService orderService = new OrderService(chickenService);

    Scanner myScanner = new Scanner(System.in);

    public void displayOrderScreen() {

        Order order = orderService.orderCreation();

        boolean isOrderRunning = true;

        ChickenBuilderScreen chickenBuilder = new ChickenBuilderScreen(orderService);
        DrinkScreen drinkScreen = new DrinkScreen(orderService);
        SideScreen sideScreen = new SideScreen(orderService);
        CheckoutScreen checkoutScreen = new CheckoutScreen(orderService, chickenService);
        SignatureScreen signatureScreen = new SignatureScreen(orderService, chickenService);

        while (isOrderRunning) {

            System.out.println(
                    TextFormatter.bold(TextFormatter.gold(
                    """
                    \n
                    ________________________
                    ||   Ordering Menu    ||
                    ||--------------------||
                    || 1) Add Chicken     ||
                    || 2) Signature Items ||
                    || 3) Add Drink       ||
                    || 4) Add Side        ||
                    || 5) Checkout        ||
                    || 0) Cancel Order    ||
                    ||____________________||
                    """)
            ));

            System.out.print(TextFormatter.bold(TextFormatter.cyan("Selection: ")));
            String userChoice = myScanner.nextLine().trim();

            switch (userChoice) {
                case "1" -> order = chickenBuilder.displayChickenBuilder(order);
                case "2" -> order = signatureScreen.displaySignatureScreen(order);
                case "3" -> order = drinkScreen.displayDrinkScreen(order);
                case "4" -> order = sideScreen.displaySideScreen(order);
                case "5" -> {
                    checkoutScreen.displayCheckoutScreen(order);
                    isOrderRunning = false;
                }
                case "0" -> {
                    orderService.cancelOrder(order.getOrderId());
                    isOrderRunning = false;
                }
            }
        }
    }
}
