package com.yearupunited.goldencrunchchicken.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table (name = "receipts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long receiptId;

    /// Only ONE receipt can belong to a single order, has a unique id
    @OneToOne
    @JoinColumn (name = "order_id", unique = true)
    private Order order;

    /// For text file later on
    private String filename;

    /// Records when receipt was created
    private LocalDateTime receiptDate;

    /// Stores final total cost of order on receipt
    private BigDecimal totalPrice;
}
