package com.yearupunited.goldencrunchchicken.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "sides")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sides {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long sideId;

    @Column (nullable = false)
    private String sideName;

    @Column (nullable = false)
    private BigDecimal sidePrice;

    /// Many sides can belong to a single order, same as Drink
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "order_id")
    @JsonBackReference
    private Order order;
}
