package com.pluralsight.goldencrunchchicken.model;

import com.pluralsight.goldencrunchchicken.model.enums.ChickenCut;
import com.pluralsight.goldencrunchchicken.model.enums.PrepStyle;
import com.pluralsight.goldencrunchchicken.model.enums.SauceType;
import com.pluralsight.goldencrunchchicken.model.enums.ToppingType;

import java.math.BigDecimal;

public class HoneyGlazeChicken extends Chicken{

    public HoneyGlazeChicken() {
        super();

        /// Setting topping and sauce values
        Toppings scallions = new Toppings();
        scallions.setToppingName("Scallions");
        scallions.setToppingType(ToppingType.REGULAR);

        Toppings goldenGlaze = new Toppings();
        goldenGlaze.setToppingName("GOLDEN GLAZE");
        goldenGlaze.setToppingType(ToppingType.PREMIUM);
        goldenGlaze.setPriceWings(BigDecimal.valueOf(0.50));
        goldenGlaze.setPriceDrumsticks(BigDecimal.valueOf(0.50));
        goldenGlaze.setPriceBoneless(BigDecimal.valueOf(0.50));
        goldenGlaze.setPriceWhole(BigDecimal.valueOf(0.50));

        Sauce honeyButter = new Sauce();
        honeyButter.setSauceType(SauceType.HONEY_BUTTER);

        Sauce koreanBbq = new Sauce();
        koreanBbq.setSauceType(SauceType.KOREAN_BBQ);

        /// Building the signature Honey Glaze Chicken
        this.setChickenCut(ChickenCut.WHOLE_CHICKEN);
        this.setPrepStyle(PrepStyle.ORIGINAL_CRISPY);
        this.getSauces().add(honeyButter);
        this.getSauces().add(koreanBbq);
        this.getToppings().add(scallions);
        this.getToppings().add(goldenGlaze);
        this.setTossedInSauce(true);
    }
}
