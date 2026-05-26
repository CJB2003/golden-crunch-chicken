package com.pluralsight.goldencrunchchicken.model;

import com.pluralsight.goldencrunchchicken.model.enums.SideType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sides {

    private Long sideId;

    /// Name of the side
    private SideType sideType;

    /// Price of side
    private BigDecimal sidePrice;

    /// Many sides can belong to a single order, same as Drink
    private Order order;
}
