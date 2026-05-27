package com.pluralsight.goldencrunchchicken.cli.screens;

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

        while (isOrderRunning) {

            System.out.println(
                    TextFormatter.bold(TextFormatter.gold(
                            """
                            \n
                            _______________________
                            ||   Ordering Menu   ||
                            =======================
                            || 1) Add Chicken    ||
                            || 2) Add Drink      ||
                            || 3) Add Side       ||
                            || 4) Checkout       ||
                            || 0) Cancel Order   ||
                            =======================
                            """)
                    ));

            System.out.print(TextFormatter.bold(TextFormatter.cyan("Selection: ")));
            String userChoice = myScanner.nextLine();

            switch (userChoice) {
                case "1" -> order = chickenBuilder.displayChickenBuilder(order);
                case "2" -> order = drinkScreen.displayDrinkScreen(order);
                case "3" -> order = sideScreen.displaySideScreen(order);
                case "4" -> {
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
