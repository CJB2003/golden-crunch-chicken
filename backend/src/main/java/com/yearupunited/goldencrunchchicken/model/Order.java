package com.yearupunited.goldencrunchchicken.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yearupunited.goldencrunchchicken.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    /// Auto-incrementing ID for orderId
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long orderId;

    /// Storing the time that order was made
    @Column (nullable = false)
    private LocalDate orderDate;

    /// Shows order status (ACTIVE, COMPLETED, or CANCELLED)
    @Enumerated (EnumType.STRING)
    @Column (nullable = false)
    private OrderStatus orderStatus;

    /**
     * JSON serializes chickenItems. When orders are made, cascade ensures that everything part of that
     * order saves as well (the chicken, drink, sides, etc) or if the order gets deleted, everything follows.
     * OrphanRemoval just deletes items without a parent from the DB. If a drink gets removed from order,
     * it is no longer part of the order therefore it is removed through orphanRemoval. Same pattern for
     * drinks and sides
      */
    @OneToMany (mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Chicken> chickenItems = new ArrayList<>();

    @OneToMany (mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Drink> drinks = new ArrayList<>();

    @OneToMany (mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Sides> sides = new ArrayList<>();

    /**
     * I have a price variable both here and Receipt because this is a flexible price that can be changed
     * depending on what is added or removed from order, while receipt is the final total that cannot be changed.
      */
    private BigDecimal calculatedPrice;
}
