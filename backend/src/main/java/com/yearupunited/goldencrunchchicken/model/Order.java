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

    @OneToMany (mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Chicken> chickenItems = new ArrayList<>();

    @OneToMany (mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Drink> drinks = new ArrayList<>();

    @OneToMany (mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Sides> sides = new ArrayList<>();

    private BigDecimal totalPrice;
}
