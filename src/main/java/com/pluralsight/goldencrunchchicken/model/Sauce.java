package com.pluralsight.goldencrunchchicken.model;

import com.pluralsight.goldencrunchchicken.model.enums.SauceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sauce {

    /// Auto-incremented id for sauces
    private Long sauceId;

    /// Rejects a null input, calls sauce type enum class
    private SauceType sauceType;
}
