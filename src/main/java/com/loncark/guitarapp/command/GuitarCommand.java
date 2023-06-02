package com.loncark.guitarapp.command;

import com.loncark.guitarapp.model.Material;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class GuitarCommand {
    @NotBlank
    @Size(min = 4, max = 4)
    String code;

    @NotNull
    Material body;
    @NotNull
    Material neck;
    @NotBlank
    String name;
    @Positive
    @Digits(integer=5, fraction=2)
    BigDecimal price;
    @PositiveOrZero
    Long stock;

    public GuitarCommand(String code, Material body, Material neck, String name, BigDecimal price, Long stock) {
        this.code = code;
        this.body = body;
        this.neck = neck;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getCode() {
        return code;
    }

    public Material getBody() {
        return body;
    }

    public Material getNeck() {
        return neck;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getStock() {
        return stock;
    }
}
