package com.pluralsight.goldencrunchchicken.cli.screens;

import com.pluralsight.goldencrunchchicken.model.Chicken;
import com.pluralsight.goldencrunchchicken.model.Order;
import com.pluralsight.goldencrunchchicken.model.Sauce;
import com.pluralsight.goldencrunchchicken.model.Toppings;
import com.pluralsight.goldencrunchchicken.model.enums.ChickenCut;
import com.pluralsight.goldencrunchchicken.model.enums.PrepStyle;
import com.pluralsight.goldencrunchchicken.model.enums.SauceType;
import com.pluralsight.goldencrunchchicken.model.enums.ToppingType;
import com.pluralsight.goldencrunchchicken.service.OrderService;
import com.pluralsight.goldencrunchchicken.util.TextFormatter;

import java.math.BigDecimal;
import java.util.Scanner;

public class ChickenBuilderScreen {

    Scanner myScanner = new Scanner(System.in);

    private final OrderService orderService;

    public ChickenBuilderScreen(OrderService orderService) {
        this.orderService = orderService;
    }

    public Order displayChickenBuilder(Order chickenOrder) {

        boolean isBuildingChicken = true;

        while (isBuildingChicken) {

            Chicken chicken = new Chicken();

            System.out.print(
                    TextFormatter.bold(TextFormatter.gold(
                            """
                            \n
                            _________________________________________________________________
                            ||                     CHICKEN BUILDER MENU                    ||
                            ||                     (8 PIECES PER ORDER)                    ||
                            ||=============================================================||
                            || [1. WINGS] [2. DRUMSTICKS] [3. BONELESS] [4. WHOLE CHICKEN] ||
                            ||    $8.99        $9.99         $10.50           $20.00       ||
                            ||_____________________________________________________________||
                            """)
                    )
            );

            ChickenCut chickenCut = ChickenCut.WINGS;

            boolean validCut = false;

            while (!validCut) {

                System.out.print(TextFormatter.bold(TextFormatter.cyan("\nWhat cut of chicken would you like [1-4]: ")));
                String userChickenCut = myScanner.nextLine().toUpperCase().trim();

                switch (userChickenCut) {
                    case "1" -> {
                        chickenCut = ChickenCut.WINGS;
                        validCut = true;
                    }
                    case "2" -> {
                        chickenCut = ChickenCut.DRUMSTICKS;
                        validCut = true;
                    }
                    case "3" -> {
                        chickenCut = ChickenCut.BONELESS;
                        validCut = true;
                    }
                    case "4" -> {
                        chickenCut = ChickenCut.WHOLE_CHICKEN;
                        validCut = true;
                    }
                    default -> System.out.println(TextFormatter.bold(TextFormatter.red("\nInvalid chicken cut. Try again.")));
                }
            }
            chicken.setChickenCut(chickenCut);

            System.out.println(
                    TextFormatter.bold(TextFormatter.gold(
                            """
                            \n
                            _________________________________________________________________
                            ||                      PREPARATION STYLES                     ||
                            ||-------------------------------------------------------------||
                            ||  [1. ORIGINAL CRISPY]    [2. EXTRA CRISPY]    [3. GRILLED]  ||
                            ||_____________________________________________________________||
                            """)
                    )
            );

            PrepStyle prepStyle = PrepStyle.ORIGINAL_CRISPY;

            boolean validPrep = false;

            while (!validPrep) {

                System.out.print(TextFormatter.bold(TextFormatter.cyan("How would you like your chicken prepped (1-3): ")));
                String userPrepStyle = myScanner.nextLine().toUpperCase().trim();

                switch (userPrepStyle) {
                    case "1" -> {
                        prepStyle = PrepStyle.ORIGINAL_CRISPY;
                        validPrep = true;
                    }
                    case "2" -> {
                        prepStyle = PrepStyle.EXTRA_CRISPY;
                        validPrep = true;
                    }
                    case "3" -> {
                        prepStyle = PrepStyle.GRILLED;
                        validPrep = true;
                    }
                    default -> System.out.println(TextFormatter.bold(TextFormatter.red("\nNot a valid prep style. Try again.\n")));
                }
            }
            chicken.setPrepStyle(prepStyle);

            System.out.println(
                    TextFormatter.bold(TextFormatter.gold(
                            """
                            \n
                            _______________________________________________________________________________
                            ||                             PREMIUM TOPPINGS                              ||
                            ||---------------------------------------------------------------------------||
                            ||        WINGS: $0.75 DRUMSTICKS: $0.99 BONELESS: $1.50 WHOLE: $2.50        ||
                            ||                                                                           ||
                            || [1. BONITO FLAKES] [2. CHEESE POWDER] [3. CORN CHEESE] [4. GARLIC BUTTER] ||
                            ||___________________________________________________________________________||
                            """)
                    )
            );

            boolean isChoosingPrem = true;

            // Created a counter to set a limit for toppings on chicken
            int toppingCounter = 0;

            // While loop and switch for premium toppings so user can choose multiple times
            while (isChoosingPrem) {

                System.out.print(TextFormatter.bold(TextFormatter.cyan("[4 MAX TOPPINGS] What premium toppings would you like [1-4] "
                        + TextFormatter.bold(TextFormatter.green("Press 0 when finished: ")))));
                String userPremToppings = myScanner.nextLine().toUpperCase().trim();

                switch (userPremToppings) {
                    case "1" -> chicken.getToppings().add(createPremiumTopping("BONITO FLAKES"));
                    case "2" -> chicken.getToppings().add(createPremiumTopping("CHEESE POWDER"));
                    case "3" -> chicken.getToppings().add(createPremiumTopping("CORN CHEESE"));
                    case "4" -> chicken.getToppings().add(createPremiumTopping("GARLIC BUTTER"));
                    case "0" -> isChoosingPrem = false;
                    default -> System.out.println(TextFormatter.bold(TextFormatter.red("\nWe don't offer that topping. Please try again.\n")));
                }

                // Topping counter increments if not case 0 or default
                if (chicken.getToppings().size() > toppingCounter) {
                    toppingCounter += 1;
                    if (toppingCounter > 4) {
                        System.out.println(TextFormatter.bold(TextFormatter.red("\nYou cannot have more than 4 toppings on your chicken.")));
                        isChoosingPrem = false;
                    }
                }
            }

            System.out.println(
                    TextFormatter.bold(TextFormatter.gold(
                            """
                            \n
                            _________________________________________________________________
                            ||                      REGULAR TOPPINGS                       ||
                            ||-------------------------------------------------------------||
                            ||      [1. SCALLIONS]  [2. SESAME SEEDS]  [3. JALAPENOS]      ||
                            ||_____________________________________________________________||
                            """)
                    )
            );

            boolean isChoosingReg = true;

            // While loop and switch for regular toppings
            while (isChoosingReg) {

                System.out.print(TextFormatter.bold(TextFormatter.cyan("[4 MAX TOPPINGS] What regular toppings would you like [1-3] "
                        + TextFormatter.bold(TextFormatter.green("Press 0 when finished: ")))));

                String userRegToppings = myScanner.nextLine().toUpperCase().trim();

                switch (userRegToppings) {
                    case "1" -> chicken.getToppings().add(createRegularTopping("SCALLIONS"));
                    case "2" -> chicken.getToppings().add(createRegularTopping("SESAME SEEDS"));
                    case "3" -> chicken.getToppings().add(createRegularTopping("JALAPENOS"));
                    case "0" -> isChoosingReg = false;
                    default -> System.out.println(TextFormatter.bold(TextFormatter.red("\nWe don't offer that topping. Please try again.\n")));
                }

                if (chicken.getToppings().size() > toppingCounter) {
                    toppingCounter += 1;
                    if (toppingCounter > 4) {
                        System.out.println(TextFormatter.bold(TextFormatter.red("\nYou cannot have more than 4 toppings on your chicken.")));
                        isChoosingReg = false;
                    }
                }
            }

            System.out.println(
                    TextFormatter.bold(TextFormatter.gold(
                            """
                            \n
                            _________________________________________________________________
                            ||                          7 SAUCES                           ||
                            ||-------------------------------------------------------------||
                            || [1. SOY GARLIC] [2. GANGJEONG] [3. GALBI] [4. HONEY BUTTER] ||
                            ||         [5. KOREAN BBQ] [6. BULGOGI] [7. HOT SPICY]         ||
                            ||_____________________________________________________________||
                            """)
                    )
            );

            boolean isChoosingSauce = true;

            int sauceCounter = 0;

            // Time to choose sauces, while loop for choosing sauces
            while (isChoosingSauce) {

                System.out.print(TextFormatter.bold(TextFormatter.cyan("[2 MAX SAUCES] What sauces would like on your chicken [1-7] "
                        + TextFormatter.bold(TextFormatter.green("Press 0 when finished: ")))));
                String userSauce = myScanner.nextLine().toUpperCase().trim();

                switch (userSauce) {
                    case "1" -> chicken.getSauces().add(createSauce(SauceType.SOY_GARLIC));
                    case "2" -> chicken.getSauces().add(createSauce(SauceType.GANG_JEONG));
                    case "3" -> chicken.getSauces().add(createSauce(SauceType.GALBI));
                    case "4" -> chicken.getSauces().add(createSauce(SauceType.HONEY_BUTTER));
                    case "5" -> chicken.getSauces().add(createSauce(SauceType.KOREAN_BBQ));
                    case "6" -> chicken.getSauces().add(createSauce(SauceType.BULGOGI));
                    case "7" -> chicken.getSauces().add(createSauce(SauceType.HOT_SPICY));
                    case "0" -> isChoosingSauce = false;
                    default -> System.out.println(TextFormatter.bold(TextFormatter.red("\nWe don't offer that sauce. Please try again.\n")));
                }
                if (chicken.getSauces().size() > sauceCounter) {
                    sauceCounter += 1;
                    if (sauceCounter > 2) {
                        System.out.println(TextFormatter.bold(TextFormatter.red("\nYou cannot have more than 2 sauces for your chicken.")));
                        isChoosingSauce = false;
                    }
                }
            }

            boolean isUserToss = false;
            while(!isUserToss) {

                // Asks user whether they would like chicken tossed in sauce or not
                System.out.print(TextFormatter.bold(TextFormatter.cyan("\nWould you like your chicken tossed in sauce or have it on the side? (Y/N) ")));
                String userToss = myScanner.nextLine();

                if (userToss.equalsIgnoreCase("y")) {
                    chicken.setTossedInSauce(true);
                    System.out.println(TextFormatter.bold(TextFormatter.green("\nYou've chosen to toss your chicken in sauce!")));
                    isUserToss = true;
                } else if (userToss.equalsIgnoreCase("n")) {
                    chicken.setTossedInSauce(false);
                    System.out.println(TextFormatter.bold(TextFormatter.green("\nYou've chosen to have your sauce on the side!")));
                    isUserToss = true;
                } else {
                    System.out.println(TextFormatter.bold(TextFormatter.red("Invalid choice. Please try again.")));
                }
            }

            boolean isUserSpecial = false;
            while(!isUserSpecial) {

                // Created a special option for the chicken, 0.50 for all chicken cuts
                System.out.print(TextFormatter.bold(TextFormatter.cyan("\nWould you like to upgrade to our special Golden Glaze today? (+0.50) (Y/N) ")));
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

                    System.out.println(TextFormatter.bold(TextFormatter.green("\nYou've chosen to upgrade to our special!")));

                    isUserSpecial = true;
                }
                else if (userSpecial.equalsIgnoreCase("n")) {

                    System.out.println(TextFormatter.bold(TextFormatter.green("\nNo upgrade today, maybe next time!")));

                    isUserSpecial = true;
                }
                else {
                    System.out.println(TextFormatter.bold(TextFormatter.red("Invalid choice. Please try again.")));
                }
            }

            chickenOrder = orderService.addChickenToOrder(chickenOrder.getOrderId(), chicken);

            boolean validChoice = false;
            while(!validChoice) {

                System.out.print(TextFormatter.bold(TextFormatter.cyan("\nWould like to add another order of chicken (Y/N): ")));
                String userAddOrNo = myScanner.nextLine().trim();

                if (userAddOrNo.equalsIgnoreCase("y")) {
                    validChoice = true;
                }
                else if (userAddOrNo.equalsIgnoreCase("n")) {
                    System.out.println(TextFormatter.bold(TextFormatter.red("\nExiting chicken builder menu...")));
                    isBuildingChicken = false;
                    validChoice = true;
                }
                else {
                    System.out.println(TextFormatter.bold(TextFormatter.red("\nPlease enter Y or N.")));
                }
            }
        }
        return chickenOrder;
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
