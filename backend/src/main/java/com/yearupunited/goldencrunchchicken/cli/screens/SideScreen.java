package com.yearupunited.goldencrunchchicken.cli.screens;

import com.yearupunited.goldencrunchchicken.model.Order;
import com.yearupunited.goldencrunchchicken.model.Sides;
import com.yearupunited.goldencrunchchicken.model.enums.SideType;
import com.yearupunited.goldencrunchchicken.service.OrderService;
import com.yearupunited.goldencrunchchicken.util.TextFormatter;

import java.math.BigDecimal;
import java.util.Scanner;

public class SideScreen {

    Scanner myScanner = new Scanner(System.in);

    private OrderService orderService;

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

                System.out.print(TextFormatter.gold("Select a side (1-5): "));
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
                    default -> System.out.println(TextFormatter.red("\nWe don't offer that side here. Please select one of the choices above."));
                }
            }
            System.out.print(TextFormatter.gold("\nWould you like to add another side (Y/N): "));
            String userAddOrNo = myScanner.nextLine().trim();

            if (!userAddOrNo.equalsIgnoreCase("y")) {
                System.out.println(TextFormatter.red("\nExiting side menu..."));
                isChoosingSide = false;
            }
            sideOrder = orderService.addSideToOrder(sideOrder.getOrderId(), sides);
        }
        return sideOrder;
    }
}
