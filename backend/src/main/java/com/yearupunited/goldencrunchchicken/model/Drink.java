package com.yearupunited.goldencrunchchicken.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yearupunited.goldencrunchchicken.model.enums.DrinkSize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table (name = "drinks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drink {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long drinkId;

    /// Drink size can be small, medium, or large
    @Enumerated (EnumType.STRING)
    @Column (nullable = false)
    private DrinkSize drinkSize;

    /// Can be a coke, sprite, water, etc
    private String drinkFlavor;

    /// Smalls are $1.99, Medium $2.50, and Large $2.99
    @Column (nullable = false)
    private BigDecimal drinkPrice;

    /// Multiple drinks can be added to an order
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "order_id")
    @JsonBackReference
    private Order order;
}
