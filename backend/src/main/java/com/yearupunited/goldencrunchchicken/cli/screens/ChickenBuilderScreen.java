package com.yearupunited.goldencrunchchicken.cli.screens;

import com.yearupunited.goldencrunchchicken.model.Chicken;
import com.yearupunited.goldencrunchchicken.model.Order;
import com.yearupunited.goldencrunchchicken.model.Sauce;
import com.yearupunited.goldencrunchchicken.model.Toppings;
import com.yearupunited.goldencrunchchicken.model.enums.ChickenCut;
import com.yearupunited.goldencrunchchicken.model.enums.PrepStyle;
import com.yearupunited.goldencrunchchicken.model.enums.SauceType;
import com.yearupunited.goldencrunchchicken.model.enums.ToppingType;
import com.yearupunited.goldencrunchchicken.service.OrderService;
import com.yearupunited.goldencrunchchicken.util.TextFormatter;

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
                TextFormatter.bold(TextFormatter.gold(
                        """
                        \n
                        _________________________________________________________________
                        ||                     CHICKEN BUILDER MENU                    ||
                        ||=============================================================||
                        ||   [WINGS]    [DRUMSTICKS]    [BONELESS]     [WHOLE CHICKEN] ||
                        ||    $8.99        $9.99         $10.50           $20.00       ||
                        ||-------------------------------------------------------------||
                        ||                      PREPARATION STYLES                     ||
                        ||-------------------------------------------------------------||
                        ||     [ORIGINAL CRISPY]     [EXTRA CRISPY]      [GRILLED]     ||
                        ||_____________________________________________________________||
                        ||                       PREMIUM TOPPINGS                      ||
                        ||-------------------------------------------------------------||
                        || WINGS: $0.75 DRUMSTICKS: $0.99 BONELESS: $1.50 WHOLE: $2.50 ||
                        ||                                                             ||
                        ||[BONITO FLAKES] [CHEESE POWDER] [CORN CHEESE] [GARLIC BUTTER]||
                        ||-------------------------------------------------------------||
                        ||                      REGULAR TOPPINGS                       ||
                        ||-------------------------------------------------------------||
                        ||          [SCALLIONS]  [SESAME SEEDS]  [JALAPENOS]           ||
                        ||_____________________________________________________________||
                        ||                          7 SAUCES                           ||
                        ||-------------------------------------------------------------||
                        || [SOY GARLIC]     [GANGJEONG]     [GALBI]     [HONEY BUTTER] ||
                        ||         [KOREAN BBQ]      [BULGOGI]    [HOT SPICY]          ||
                        ||_____________________________________________________________||
                        """)
                )
        );

        ChickenCut chickenCut = ChickenCut.WINGS;

        boolean validCut = false;

        while (!validCut) {

            System.out.print(TextFormatter.cyan("\nWhat cut of chicken would you like? "));
            String userChickenCut = myScanner.nextLine().toUpperCase().trim();

            switch (userChickenCut) {
                case "WINGS" -> {
                    chickenCut = ChickenCut.WINGS;
                    validCut = true;
                }
                case "DRUMSTICKS" -> {
                    chickenCut = ChickenCut.DRUMSTICKS;
                    validCut = true;
                }
                case "BONELESS" -> {
                    chickenCut = ChickenCut.BONELESS;
                    validCut = true;
                }
                case "WHOLE CHICKEN" -> {
                    chickenCut = ChickenCut.WHOLE_CHICKEN;
                    validCut = true;
                }
                default -> System.out.println(TextFormatter.red("\nInvalid chicken cut. Try again."));
            }
        }
        chicken.setChickenCut(chickenCut);

        PrepStyle prepStyle = PrepStyle.ORIGINAL_CRISPY;

        boolean validPrep = false;

        while (!validPrep) {

            System.out.print(TextFormatter.cyan("\nHow would you like your chicken prepped? "));
            String userPrepStyle = myScanner.nextLine().toUpperCase().trim();

            switch (userPrepStyle) {
                case "ORIGINAL CRISPY" -> {
                    prepStyle = PrepStyle.ORIGINAL_CRISPY;
                    validPrep = true;
                }
                case "EXTRA CRISPY" -> {
                    prepStyle = PrepStyle.EXTRA_CRISPY;
                    validPrep = true;
                }
                case "GRILLED" -> {
                    prepStyle = PrepStyle.GRILLED;
                    validPrep = true;
                }
                default -> System.out.println(TextFormatter.red("\nNot a valid prep style. Try again."));
            }
        }
        chicken.setPrepStyle(prepStyle);

        boolean isChoosingPrem = true;

        // Created a counter to set a limit for toppings on chicken
        int toppingCounter = 0;

        // While loop and switch for premium toppings so user can choose multiple times
        while (isChoosingPrem){

            System.out.print(TextFormatter.cyan("\nWhat premium toppings would you like (4 MAX)? (Press 0 when finished) "));

            String userPremToppings = myScanner.nextLine().toUpperCase().trim();

            switch (userPremToppings) {
                case "BONITO FLAKES" -> chicken.getToppings().add(createPremiumTopping("BONITO FLAKES"));
                case "CHEESE POWDER" -> chicken.getToppings().add(createPremiumTopping("CHEESE POWDER"));
                case "CORN CHEESE" -> chicken.getToppings().add(createPremiumTopping("CORN CHEESE"));
                case "GARLIC BUTTER" -> chicken.getToppings().add(createPremiumTopping("GARLIC BUTTER"));
                case "0" -> isChoosingPrem = false;
                default -> System.out.println(TextFormatter.red("\nWe don't offer that topping. Please try again."));
            }

            // Topping counter increments if not case 0 or default
            if (!userPremToppings.equals("0") && !chicken.getToppings().isEmpty()) {
                toppingCounter += 1;
                if (toppingCounter >= 4) {
                    System.out.println(TextFormatter.red("\nYou cannot have more than 4 toppings on your chicken."));
                    isChoosingPrem = false;
                }
            }
        }

        boolean isChoosingReg = true;

        // While loop and switch for regular toppings
        while (isChoosingReg) {

            System.out.print(TextFormatter.cyan("\nWhat regular toppings would you like (4 MAX)? (Press 0 when finished) "));
            String userRegToppings = myScanner.nextLine().toUpperCase().trim();

            switch (userRegToppings) {
                case "SCALLIONS" -> chicken.getToppings().add(createRegularTopping("SCALLIONS"));
                case "SESAME SEEDS" -> chicken.getToppings().add(createRegularTopping("SESAME SEEDS"));
                case "JALAPENOS" -> chicken.getToppings().add(createRegularTopping("JALAPENOS"));
                case "0" -> isChoosingReg = false;
                default -> System.out.println(TextFormatter.red("\nWe don't offer that topping. Please try again."));
            }

            if (!userRegToppings.equals("0") && !chicken.getToppings().isEmpty()) {
                toppingCounter += 1;
                if (toppingCounter >= 4) {
                    System.out.println(TextFormatter.red("\nYou cannot have more than 4 toppings on your chicken."));
                    isChoosingReg = false;
                }
            }
        }

        boolean isChoosingSauce = true;

        int sauceCounter = 0;

        // Time to choose sauces, while loop for choosing sauces
        while (isChoosingSauce) {

            System.out.print(TextFormatter.cyan("\nWhat sauces would like on your chicken (2 MAX)? (Press 0 when finished) "));
            String userSauce = myScanner.nextLine().toUpperCase().trim();

            switch (userSauce) {
                case "SOY GARLIC" -> chicken.getSauces().add(createSauce(SauceType.SOY_GARLIC));
                case "GANGJEONG" -> chicken.getSauces().add(createSauce(SauceType.GANG_JEONG));
                case "GALBI" -> chicken.getSauces().add(createSauce(SauceType.GALBI));
                case "HONEY BUTTER" -> chicken.getSauces().add(createSauce(SauceType.HONEY_BUTTER));
                case "KOREAN BBQ" -> chicken.getSauces().add(createSauce(SauceType.KOREAN_BBQ));
                case "BULGOGI" -> chicken.getSauces().add(createSauce(SauceType.BULGOGI));
                case "HOT SPICY" -> chicken.getSauces().add(createSauce(SauceType.HOT_SPICY));
                case "0" -> isChoosingSauce = false;
                default -> System.out.println(TextFormatter.red("\nWe don't offer that sauce. Please try again."));
            }
            if (!userSauce.equals("0") && !chicken.getSauces().isEmpty()) {
                sauceCounter += 1;
                if (sauceCounter >= 2) {
                    System.out.println(TextFormatter.red("\nYou cannot have more than 2 sauces for your chicken."));
                    isChoosingSauce = false;
                }
            }
        }

        // Asks user whether they would like chicken tossed in sauce or not
        System.out.print(TextFormatter.cyan("\nWould you like your chicken tossed in sauce or have it on the side? (Y/N) "));
        String userToss = myScanner.nextLine();
        chicken.setTossedInSauce(userToss.equalsIgnoreCase("y"));

        // Created a special option for the chicken, 0.50 for all chicken cuts
        System.out.print(TextFormatter.cyan("\nWould you like to upgrade to our special Golden Glaze today? (+0.50) (Y/N) "));
        String userSpecial = myScanner.nextLine();

        if (userSpecial.equalsIgnoreCase("y")) {

            Toppings specialTopping = new Toppings();

            specialTopping.setToppingName("GOLDEN GLAZE");
            specialTopping.setToppingType(ToppingType.PREMIUM);
            specialTopping.setPriceWings(BigDecimal.valueOf(0.50));
            specialTopping.setPriceDrumsticks(BigDecimal.valueOf(0.50));
            specialTopping.setPriceBoneless(BigDecimal.valueOf(0.50));
            specialTopping.setPriceWhole(BigDecimal.valueOf(0.50));
            chicken.getToppings().add(specialTopping);
        }

        return orderService.addChickenToOrder(order.getOrderId(), chicken);
    }

    /// Created a helper method that sets up the prices and topping type of premium topping
    private Toppings createPremiumTopping(String toppingName) {

        Toppings premToppings = new Toppings();

        premToppings.setToppingName(toppingName);
        premToppings.setToppingType(ToppingType.PREMIUM);
        premToppings.setPriceWings(BigDecimal.valueOf(0.75));
        premToppings.setPriceDrumsticks(BigDecimal.valueOf(0.99));
        premToppings.setPriceBoneless(BigDecimal.valueOf(1.50));
        premToppings.setPriceWhole(BigDecimal.valueOf(2.50));
        return premToppings;
    }

    /// Helper method for regular toppings
    private Toppings createRegularTopping(String toppingName) {

        Toppings regToppings = new Toppings();

        regToppings.setToppingName(toppingName);
        regToppings.setToppingType(ToppingType.REGULAR);
        return regToppings;
    }

    /// Made helper method for sauces
    private Sauce createSauce(SauceType sauceType) {

        Sauce sauce = new Sauce();
        sauce.setSauceType(sauceType);
        return sauce;
    }
}
