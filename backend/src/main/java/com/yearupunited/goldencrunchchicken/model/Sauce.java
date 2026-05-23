package com.yearupunited.goldencrunchchicken.model;

import com.yearupunited.goldencrunchchicken.model.enums.SauceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "sauces")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sauce {

    /// Auto-incremented id for sauces
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long sauceId;

    /// Rejects a null input, calls sauce type enum class
    @Enumerated
    @Column (nullable = false)
    private SauceType sauceType;
}
