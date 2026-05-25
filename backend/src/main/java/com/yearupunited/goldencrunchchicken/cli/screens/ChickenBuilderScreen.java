package com.yearupunited.goldencrunchchicken.cli.screens;

import com.yearupunited.goldencrunchchicken.model.Chicken;
import com.yearupunited.goldencrunchchicken.model.Order;
import com.yearupunited.goldencrunchchicken.model.Toppings;
import com.yearupunited.goldencrunchchicken.model.enums.ChickenCut;
import com.yearupunited.goldencrunchchicken.model.enums.PrepStyle;
import com.yearupunited.goldencrunchchicken.model.enums.ToppingType;
import com.yearupunited.goldencrunchchicken.service.OrderService;
import com.yearupunited.goldencrunchchicken.util.TextFormatter;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;
import java.util.Scanner;

public class ChickenBuilderScreen {

    Scanner myScanner = new Scanner(System.in);

    private OrderService orderService;

    public ChickenBuilderScreen(OrderService orderService) {
        this.orderService = orderService;
    }

    public Order displayChickenBuilder(Order order) {

        Chicken chicken = new Chicken();

        System.out.print(
                TextFormatter.bold(TextFormatter.gold("""
                        _________________________________________________________________
                        ||                     CHICKEN BUILDER MENU                    ||
                        ||=============================================================||
                        ||  [ WINGS ]  [ DRUMSTICKS ]  [ BONELESS ]  [ WHOLE CHICKEN ] ||
                        ||    $8.99        $9.99         $10.50           $20.00       ||
                        ||-------------------------------------------------------------||
                        ||                      PREPARATION STYLES                     ||
                        ||-------------------------------------------------------------||
                        ||    [ ORIGINAL CRISPY ]     [EXTRA CRISPY]     [GRILLED]     ||
                        ||_____________________________________________________________||
                        ||                       PREMIUM TOPPINGS                      ||
                        ||-------------------------------------------------------------||
                        || WINGS: $0.75 DRUMSTICKS: $0.99 BONELESS: $1.50 WHOLE: $2.50 ||
                        ||                                                             ||
                        ||[BONITO FLAKES] [CHEESE POWDER] [CORN CHEESE] [GARLIC BUTTER]||
                        ||-------------------------------------------------------------||
                        ||                      REGULAR TOPPINGS                       ||
                        ||-------------------------------------------------------------||
                        ||   [SCALLIONS] [SESAME SEEDS] [PICKLED RADISH] [JALAPENOS]   ||
                        ||_____________________________________________________________||
                        ||                          7 SAUCES                           ||
                        ||-------------------------------------------------------------||
                        || [SOY GARLIC]     [GANGJEONG]     [GALBI]     [HONEY BUTTER] ||
                        ||         [KOREAN BBQ]      [BULGOGI]    [HOT SPICY]          ||
                        ||_____________________________________________________________||
                        """)
                )
        );
        System.out.print(TextFormatter.gold("What cut of chicken would you like: "));
        String userChickenCut = myScanner.nextLine().toUpperCase().trim();

        ChickenCut chickenCut = ChickenCut.WINGS;

        switch (userChickenCut) {
            case "WINGS" -> chickenCut = ChickenCut.WINGS;
            case "DRUMSTICKS" -> chickenCut = ChickenCut.DRUMSTICKS;
            case "BONELESS" -> chickenCut = ChickenCut.BONELESS;
            case "WHOLE CHICKEN" -> chickenCut = ChickenCut.WHOLE_CHICKEN;
            default -> System.out.println(TextFormatter.red("Invalid chicken cut. Try again."));
        }
        chicken.setChickenCut(chickenCut);

        System.out.print(TextFormatter.gold("\nHow would you like your chicken prepped? "));
        String userPrepStyle = myScanner.nextLine().toUpperCase().trim();

        PrepStyle prepStyle = PrepStyle.ORIGINAL_CRISPY;

        switch (userPrepStyle) {
            case "ORIGINAL CRISPY" -> prepStyle = PrepStyle.ORIGINAL_CRISPY;
            case "EXTRA CRISPY" -> prepStyle = PrepStyle.EXTRA_CRISPY;
            case "GRILLED" -> prepStyle = PrepStyle.GRILLED;
            default -> System.out.println(TextFormatter.red("Not a valid prep style. Try again."));
        }
        chicken.setPrepStyle(prepStyle);

        boolean isChoosingPrem = true;

        while (isChoosingPrem){

            System.out.print("What premium toppings would you like? (Press 0 when finished) ");

            String userPremToppings = myScanner.nextLine().toUpperCase().trim();

            switch (userPremToppings) {
                case "BONITO FLAKES" -> chicken.getToppings().add(createPremiumTopping("BONITO FLAKES"));
                case "CHEESE POWDER" -> chicken.getToppings().add(createPremiumTopping("CHEESE POWDER"));
                case "CORN CHEESE" -> chicken.getToppings().add(createPremiumTopping("CORN CHEESE"));
                case "GARLIC BUTTER" -> chicken.getToppings().add(createPremiumTopping("GARLIC BUTTER"));
                case "0" -> isChoosingPrem = false;
                default -> System.out.println("We don't offer that topping. Please try again.");
            }
        }

        boolean isChoosingReg = true;

        while (isChoosingReg) {

            Toppings regTopping = new Toppings();

            System.out.println("What regular toppings would you like? (Press 0 when finished) ");
            String userRegToppings = myScanner.nextLine().toUpperCase().trim();

            switch (userRegToppings) {
                case "SCALLIONS" -> {
                    regTopping.setToppingName("SCALLIONS");
                    regTopping.setToppingType(ToppingType.REGULAR);
                    chicken.getToppings().add(regTopping);
                }
                case "SESAME SEEDS" -> {
                    regTopping.setToppingName("SESAME SEEDS");
                    regTopping.setToppingType(ToppingType.REGULAR);
                    chicken.getToppings().add(regTopping);
                }
                case "PICKLED RADISH" -> {
                    regTopping.setToppingName("PICKLED RADISH");
                    regTopping.setToppingType(ToppingType.REGULAR);
                    chicken.getToppings().add(regTopping);
                }
                case "JALAPENOS" -> {
                    regTopping.setToppingName("JALAPENOS");
                    regTopping.setToppingType(ToppingType.REGULAR);
                    chicken.getToppings().add(regTopping);
                }
                case "0" -> isChoosingReg = false;
                default -> System.out.println("We don't offer that topping. Please try again.");
            }
        }
    }

    /// Created a helper method that sets up the prices and topping type of premium topping
    private Toppings createPremiumTopping(String toppingName) {

        Toppings toppings = new Toppings();

        toppings.setToppingName(toppingName);
        toppings.setToppingType(ToppingType.PREMIUM);
        toppings.setPriceWings(BigDecimal.valueOf(0.75));
        toppings.setPriceDrumsticks(BigDecimal.valueOf(0.99));
        toppings.setPriceBoneless(BigDecimal.valueOf(1.50));
        toppings.setPriceWhole(BigDecimal.valueOf(2.50));
        return toppings;
    }
}
