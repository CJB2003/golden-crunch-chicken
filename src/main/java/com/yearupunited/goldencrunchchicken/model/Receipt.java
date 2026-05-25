package com.yearupunited.goldencrunchchicken.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {

    private Long receiptId;

    /// Only ONE receipt can belong to a single order, has a unique id
    private Order order;

    /// For text file later on
    private String filename;

    /// Records when receipt was created
    private LocalDateTime receiptDate;

    /// Stores final total cost of order on receipt
    private BigDecimal totalPrice;
}
