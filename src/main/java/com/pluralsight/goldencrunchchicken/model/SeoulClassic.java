package com.pluralsight.goldencrunchchicken.model;

import com.pluralsight.goldencrunchchicken.model.enums.ChickenCut;
import com.pluralsight.goldencrunchchicken.model.enums.PrepStyle;
import com.pluralsight.goldencrunchchicken.model.enums.SauceType;
import com.pluralsight.goldencrunchchicken.model.enums.ToppingType;

public class SeoulClassic extends Chicken{

    public SeoulClassic() {
        super();

        /// Setting sauce and topping values
        Sauce soyGarlic = new Sauce();
        soyGarlic.setSauceType(SauceType.SOY_GARLIC);

        Sauce honeyButter = new Sauce();
        honeyButter.setSauceType(SauceType.HONEY_BUTTER);

        Toppings sesameSeeds = new Toppings();
        sesameSeeds.setToppingName("Sesame Seeds");
        sesameSeeds.setToppingType(ToppingType.REGULAR);

        Toppings scallions = new Toppings();
        scallions.setToppingName("Scallions");
        scallions.setToppingType(ToppingType.REGULAR);

        /// Building the signature Seoul Classic
        this.setChickenCut(ChickenCut.BONELESS);
        this.setPrepStyle(PrepStyle.ORIGINAL_CRISPY);
        this.getSauces().add(soyGarlic);
        this.getSauces().add(honeyButter);
        this.getToppings().add(sesameSeeds);
        this.getToppings().add(scallions);
        this.setTossedInSauce(true);
    }
}
