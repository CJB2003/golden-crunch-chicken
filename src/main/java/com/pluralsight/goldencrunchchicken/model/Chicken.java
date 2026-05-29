package com.pluralsight.goldencrunchchicken.model;

import com.pluralsight.goldencrunchchicken.model.enums.ChickenCut;
import com.pluralsight.goldencrunchchicken.model.enums.PrepStyle;
import com.pluralsight.goldencrunchchicken.model.enums.ToppingType;
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

        // Switched to a for loop so I can check position and remove at end
        for (int i = 0; i < toppings.size(); i++) {

            toppingStuff.append(toppings.get(i).getToppingName());
            if (toppings.get(i).getToppingType() == ToppingType.PREMIUM) {
                toppingStuff.append(" [PREMIUM]");
            }
            if (i < toppings.size() - 1) {
                toppingStuff.append(", ");
            }
        }
        return "Type of cut: " + chickenCut.toString().replace("_", " ")
                + "\nToppings: " + toppingStuff
                + "\nPrep style: " + prepStyle.toString().replace("_", " ")
                + "\n" + (tossedInSauce ? "[Tossed in sauce]" : "[Sauce on side]");
    }
}
