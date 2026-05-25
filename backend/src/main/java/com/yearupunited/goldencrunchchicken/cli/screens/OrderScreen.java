package com.yearupunited.goldencrunchchicken.cli.screens;

import com.yearupunited.goldencrunchchicken.model.Order;
import com.yearupunited.goldencrunchchicken.service.ChickenService;
import com.yearupunited.goldencrunchchicken.service.OrderService;
import com.yearupunited.goldencrunchchicken.util.TextFormatter;

import java.util.Scanner;

public class OrderScreen {

    private ChickenService chickenService = new ChickenService();
    private OrderService orderService = new OrderService(chickenService);

    Scanner myScanner = new Scanner(System.in);

    public void displayOrderScreen() {

        Order order = orderService.orderCreation();

        boolean isOrderRunning = true;

        ChickenBuilderScreen chickenBuilder = new ChickenBuilderScreen(orderService);

        while (isOrderRunning) {

            System.out.println(
                    TextFormatter.bold(TextFormatter.gold(
                    """
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

            System.out.print(TextFormatter.gold("Selection"));
            String userChoice = myScanner.nextLine();

            switch (userChoice) {
                case "1" -> order = chickenBuilder.displayChickenBuilder(order);
                case "2" ->
            }
        }
    }
}
