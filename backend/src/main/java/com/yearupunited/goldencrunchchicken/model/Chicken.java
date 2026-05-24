package com.yearupunited.goldencrunchchicken.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yearupunited.goldencrunchchicken.model.enums.ChickenCut;
import com.yearupunited.goldencrunchchicken.model.enums.PrepStyle;
import com.yearupunited.goldencrunchchicken.model.enums.ToppingType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chicken")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chicken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chickenId;

    /// Chicken cuts include Wings, drumsticks, boneless, or a whole chicken
    @Enumerated(EnumType.STRING)
    @Column (nullable = false)
    private ChickenCut chickenCut;

    /// Prep styles can be Crispy, Extra Crispy, or Grilled
    @Enumerated (EnumType.STRING)
    @Column (nullable = false)
    private PrepStyle prepStyle;

    /// Customer's chicken can have multiple toppings if they wanted
    @ManyToMany
    @JoinTable (
            name = "chicken_toppings",
            joinColumns = @JoinColumn (name = "chicken_id"),
            inverseJoinColumns = @JoinColumn (name = "topping_id")
    )
    private List<Toppings> toppings = new ArrayList<>();

    /// Customer's chicken can have multiple sauces
    @ManyToMany
    private List<Sauce> sauces = new ArrayList<>();

    private boolean tossedInSauce;

    /// Many orders of chicken can be added to the order
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "order_id")
    @JsonBackReference
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
        return "Type of cut: " + chickenCut + " | Toppings: " + toppingStuff + " | " + "Prep style: " + prepStyle + " | "
                + (tossedInSauce ? "[Tossed in sauce]" : "[Sauce on side]");
    }
}
