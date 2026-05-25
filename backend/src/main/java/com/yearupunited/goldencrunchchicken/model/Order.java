package com.yearupunited.goldencrunchchicken.model;

import com.yearupunited.goldencrunchchicken.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    /// Auto-incrementing ID for orderId
    private Long orderId;

    /// Storing the time that order was made
    private LocalDate orderDate;

    /// Shows order status (ACTIVE, COMPLETED, or CANCELLED)
    private OrderStatus orderStatus;

    private List<Chicken> chickenItems = new ArrayList<>();

    private List<Drink> drinks = new ArrayList<>();

    private List<Sides> sides = new ArrayList<>();

    /**
     * I have a price variable both here and Receipt because this is a flexible price that can be changed
     * depending on what is added or removed from order, while receipt is the final total that cannot be changed.
      */
    private BigDecimal calculatedPrice;
}
