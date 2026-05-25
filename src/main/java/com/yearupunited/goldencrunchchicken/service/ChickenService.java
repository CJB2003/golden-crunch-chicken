package com.yearupunited.goldencrunchchicken.service;

import com.yearupunited.goldencrunchchicken.model.Chicken;
import com.yearupunited.goldencrunchchicken.model.Toppings;
import com.yearupunited.goldencrunchchicken.model.enums.ToppingType;

import java.math.BigDecimal;

public class ChickenService {

    public BigDecimal calculatedChickenPrice(Chicken chicken) {

        // Initializing a default variable for chickenPrice
        BigDecimal chickenPrice = BigDecimal.ZERO;

        // Cases switch between cuts of chicken and assigns the price of the chicken a value
        switch (chicken.getChickenCut()) {
            case WINGS -> chickenPrice = BigDecimal.valueOf(8.99);
            case DRUMSTICKS -> chickenPrice = BigDecimal.valueOf(9.99);
            case BONELESS -> chickenPrice = BigDecimal.valueOf(10.50);
            case WHOLE_CHICKEN -> chickenPrice = BigDecimal.valueOf(20.00);
            default -> { System.out.println("Invalid cut of chicken."); return BigDecimal.ZERO; }
        }

        // Iterates through toppings, if topping type is PREMIUM, there is an upcharge added depending on cut
        for (Toppings toppings : chicken.getToppings()) {

            if (toppings.getToppingType().equals(ToppingType.PREMIUM)) {
                switch (chicken.getChickenCut()) {
                    case WINGS -> chickenPrice = chickenPrice.add(BigDecimal.valueOf(0.75));
                    case DRUMSTICKS -> chickenPrice = chickenPrice.add(BigDecimal.valueOf(0.99));
                    case BONELESS -> chickenPrice = chickenPrice.add(BigDecimal.valueOf(1.50));
                    case WHOLE_CHICKEN -> chickenPrice = chickenPrice.add(BigDecimal.valueOf(2.50));
                }
            }
        }
        return chickenPrice;
    }
}
