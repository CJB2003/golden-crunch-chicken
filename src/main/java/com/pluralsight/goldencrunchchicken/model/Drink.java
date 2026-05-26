package com.pluralsight.goldencrunchchicken.model;

import com.pluralsight.goldencrunchchicken.model.enums.DrinkSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drink {

    private Long drinkId;

    /// Drink size can be small, medium, or large
    private DrinkSize drinkSize;

    /// Can be a coke, sprite, water, etc
    private String drinkFlavor;

    /// Smalls are $1.99, Medium $2.50, and Large $2.99
    private BigDecimal drinkPrice;

    /// Multiple drinks can be added to an order
    private Order order;
}
