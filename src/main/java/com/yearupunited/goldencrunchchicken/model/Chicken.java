package com.yearupunited.goldencrunchchicken.model;

import com.yearupunited.goldencrunchchicken.model.enums.ChickenCut;
import com.yearupunited.goldencrunchchicken.model.enums.PrepStyle;
import com.yearupunited.goldencrunchchicken.model.enums.ToppingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chicken {

    private Long chickenId;

    /// Chicken cuts include Wings, drumsticks, boneless, or a whole chicken
    private ChickenCut chickenCut;

    /// Prep styles can be Crispy, Extra Crispy, or Grilled
    private PrepStyle prepStyle;

    /// Customer's chicken can have multiple toppings if they wanted
    private List<Toppings> toppings = new ArrayList<>();

    /// Customer's chicken can have multiple sauces
    private List<Sauce> sauces = new ArrayList<>();

    private boolean tossedInSauce;

    /// Many orders of chicken can be added to the order
    private Order order;

    /// Returns a line that displays type of cut, prep style, and whether the user chose to have sauce on the side or tossed with the chicken
    public String customizedChicken() {

        StringBuilder toppingStuff = new StringBuilder();
        for (Toppings topping : toppings) {

            toppingStuff.append(topping.getToppingName());
            if (topping.getToppingType() == ToppingType.PREMIUM) {
                toppingStuff.append(" [PREMIUM]");
            }
            toppingStuff.append(", ");
        }
        return "Type of cut: " + chickenCut.toString().replace("_", " ")
                + " | Toppings: " + toppingStuff
                + " | Prep style: " + prepStyle.toString().replace("_", " ")
                + " | " + (tossedInSauce ? "[Tossed in sauce]" : "[Sauce on side]");
    }
}
