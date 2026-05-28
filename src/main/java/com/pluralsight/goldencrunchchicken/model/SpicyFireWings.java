package com.pluralsight.goldencrunchchicken.model;

import com.pluralsight.goldencrunchchicken.model.enums.ChickenCut;
import com.pluralsight.goldencrunchchicken.model.enums.PrepStyle;
import com.pluralsight.goldencrunchchicken.model.enums.SauceType;
import com.pluralsight.goldencrunchchicken.model.enums.ToppingType;

public class SpicyFireWings extends Chicken{

    public SpicyFireWings() {
        super();

        /// Setting sauce and topping values
        Sauce gangjeong = new Sauce();
        gangjeong.setSauceType(SauceType.GANG_JEONG);

        Sauce hotSpicy = new Sauce();
        hotSpicy.setSauceType(SauceType.HOT_SPICY);

        Toppings jalapenos = new Toppings();
        jalapenos.setToppingName("Jalapenos");
        jalapenos.setToppingType(ToppingType.REGULAR);

        /// Building the signature Spicy Fire Wings
        this.setChickenCut(ChickenCut.WINGS);
        this.setPrepStyle(PrepStyle.EXTRA_CRISPY);
        this.getSauces().add(gangjeong);
        this.getSauces().add(hotSpicy);
        this.getToppings().add(jalapenos);
        this.setTossedInSauce(true);
    }
}
