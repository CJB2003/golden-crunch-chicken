package com.yearupunited.goldencrunchchicken.model;

import com.yearupunited.goldencrunchchicken.model.enums.ToppingType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Lombok annotations make my life easier
  */
@Entity
@Table (name = "toppings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Toppings {

    /// Unique id for SQL, automatically increments the id number
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long toppingId;

    /// Nullable just means that db will reject an empty input for topping names
    @Column (nullable = false)
    private String toppingName;

    /// Topping type can be REGULAR or PREMIUM
    @Enumerated(EnumType.STRING)
    @Column (nullable = false)
    private ToppingType toppingType;

    /// Using BigDecimal instead of double in my code for a more accurate calculation of costs
    private BigDecimal priceWings;
    private BigDecimal Drumsticks;
    private BigDecimal priceBoneless;
    private BigDecimal priceWhole;
}
