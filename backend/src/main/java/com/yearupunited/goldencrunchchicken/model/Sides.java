package com.yearupunited.goldencrunchchicken.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yearupunited.goldencrunchchicken.model.enums.SideType;
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

    /// Name of the side
    @Enumerated(EnumType.STRING)
    @Column (nullable = false)
    private SideType sideType;

    /// Price of side
    @Column (nullable = false)
    private BigDecimal sidePrice;

    /// Many sides can belong to a single order, same as Drink
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "order_id")
    @JsonBackReference
    private Order order;
}
