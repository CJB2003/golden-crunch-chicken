package com.yearupunited.goldencrunchchicken.model;

import com.yearupunited.goldencrunchchicken.model.enums.ToppingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Lombok annotations make my life easier
  */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Toppings {

    /// Unique id for SQL, automatically increments the id number
    private Long toppingId;

    /// Nullable just means that db will reject an empty input for topping names
    private String toppingName;

    /// Topping type can be REGULAR or PREMIUM
    private ToppingType toppingType;

    /// Using BigDecimal instead of double in my code for a more accurate calculation of costs
    private BigDecimal priceWings;
    private BigDecimal priceDrumsticks;
    private BigDecimal priceBoneless;
    private BigDecimal priceWhole;
}
