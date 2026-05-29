package com.pluralsight.goldencrunchchicken.screens;

import com.pluralsight.goldencrunchchicken.model.*;
import com.pluralsight.goldencrunchchicken.service.ChickenService;
import com.pluralsight.goldencrunchchicken.service.OrderService;
import com.pluralsight.goldencrunchchicken.util.TextFormatter;

import java.util.Scanner;

public class SignatureScreen {

    Scanner myScanner = new Scanner(System.in);

    private final OrderService orderService;
    private final ChickenService chickenService;

    public SignatureScreen(OrderService orderService, ChickenService chickenService) {
        this.orderService = orderService;
        this.chickenService = chickenService;
    }

    public Order displaySignatureScreen(Order sigChickenOrder) {

        System.out.println(
                TextFormatter.bold(TextFormatter.gold(
                """
                ____________________________________
                ||       SIGNATURE CHICKENS       ||
                ||________________________________||
                || 1) Seoul Classic - $14.50      ||
                || 2) Spicy Fire Wings - $11.50   ||
                || 3) Honey Glaze Wings - $24.99  ||
                ||________________________________||
                """)
                )
        );

        boolean isValid = false;
        while(!isValid) {

            System.out.print(TextFormatter.bold(TextFormatter.cyan("Selection: ")));
            String userChoice = myScanner.nextLine();

            switch (userChoice) {
                case "1" -> {
                    Chicken chicken = new SeoulClassic();
                    sigChickenOrder = orderService.addChickenToOrder(sigChickenOrder.getOrderId(), chicken);
                    isValid = true;
                }
                case "2" -> {
                    Chicken chicken = new SpicyFireWings();
                    sigChickenOrder = orderService.addChickenToOrder(sigChickenOrder.getOrderId(), chicken);
                    isValid = true;
                }
                case "3" -> {
                    Chicken chicken = new HoneyGlazeChicken();
                    sigChickenOrder = orderService.addChickenToOrder(sigChickenOrder.getOrderId(), chicken);
                    isValid = true;
                }
                default -> System.out.println(TextFormatter.bold(TextFormatter.red("Invalid choice. Try again.")));
            }
        }
        return sigChickenOrder;
    }
}
