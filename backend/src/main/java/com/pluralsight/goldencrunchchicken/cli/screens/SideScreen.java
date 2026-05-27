package com.pluralsight.goldencrunchchicken.cli.screens;

import com.pluralsight.goldencrunchchicken.model.Order;
import com.pluralsight.goldencrunchchicken.model.Sides;
import com.pluralsight.goldencrunchchicken.model.enums.SideType;
import com.pluralsight.goldencrunchchicken.service.OrderService;
import com.pluralsight.goldencrunchchicken.util.TextFormatter;

import java.math.BigDecimal;
import java.util.Scanner;

public class SideScreen {

    Scanner myScanner = new Scanner(System.in);

    private final OrderService orderService;

    public SideScreen(OrderService orderService) {
        this.orderService = orderService;
    }

    public Order displaySideScreen(Order sideOrder) {

        boolean isChoosingSide = true;

        while (isChoosingSide) {

            System.out.println(TextFormatter.bold(TextFormatter.gold(
                    """
                    ___________________________
                    ||      SIDES MENU       ||
                    ||-----------------------||
                    || 1) TTEOKBOKKI         ||
                    || 2) FRIES              ||
                    || 3) CHEESE BALLS       ||
                    || 4) PICKLED RADISH     ||
                    || 5) KIMCHI             ||
                    ||_______________________||
                    """)
            ));

            Sides sides = new Sides();

            boolean isValidSide = false;
            while (!isValidSide) {

                System.out.print(TextFormatter.bold(TextFormatter.cyan("Select a side (1-5): ")));
                String userSide = myScanner.nextLine().trim();

                switch (userSide) {
                    case "1" -> {
                        sides.setSideType(SideType.TTEOKBOKKI);
                        sides.setSidePrice(BigDecimal.valueOf(5.00));
                        isValidSide = true;
                    }
                    case "2" -> {
                        sides.setSideType(SideType.FRIES);
                        sides.setSidePrice(BigDecimal.valueOf(2.50));
                        isValidSide = true;
                    }
                    case "3" -> {
                        sides.setSideType(SideType.CHEESE_BALLS);
                        sides.setSidePrice(BigDecimal.valueOf(4.00));
                        isValidSide = true;
                    }
                    case "4" -> {
                        sides.setSideType(SideType.PICKLED_RADISH);
                        sides.setSidePrice(BigDecimal.valueOf(1.50));
                        isValidSide = true;
                    }
                    case "5" -> {
                        sides.setSideType(SideType.KIMCHI);
                        sides.setSidePrice(BigDecimal.valueOf(0.50));
                        isValidSide = true;
                    }
                    default -> System.out.println(TextFormatter.bold(TextFormatter.red("\nWe don't offer that side here. Please select one of the choices above.\n")));
                }
            }

            System.out.println(TextFormatter.bold(TextFormatter.green("\nSuccessfully added [" + sides.getSideType() + "] to checkout."
                    + "\nPrice: $" + String.format("%.2f", sides.getSidePrice()))));

            sideOrder = orderService.addSideToOrder(sideOrder.getOrderId(), sides);

            boolean validChoice = false;
            while (!validChoice) {
                System.out.print(TextFormatter.bold(TextFormatter.cyan("\nWould you like to add another side (Y/N): ")));
                String userAddOrNo = myScanner.nextLine().trim();

                if (userAddOrNo.equalsIgnoreCase("y")) {
                    validChoice = true;
                }
                else if (userAddOrNo.equalsIgnoreCase("n")) {
                    System.out.println(TextFormatter.bold(TextFormatter.red("\nExiting side menu...")));
                    isChoosingSide = false;
                    validChoice = true;
                }
                else {
                    System.out.println(TextFormatter.bold(TextFormatter.red("\nPlease enter Y or N.")));
                }
            }
        }
        return sideOrder;
    }
}
