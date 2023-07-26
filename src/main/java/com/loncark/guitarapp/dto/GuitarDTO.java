package com.loncark.guitarapp.dto;

import com.loncark.guitarapp.model.Guitar;

import java.math.BigDecimal;

public class GuitarDTO {
    Long id;
    String name;
    BigDecimal price;
    Long stock;
    String code;

    public GuitarDTO(String code, Guitar guitar) {
        this.id = guitar.getId();
        this.name = guitar.getName();
        this.price = guitar.getPrice();
        this.code = code;
        this.stock = guitar.getStock();
    }

    public GuitarDTO(Guitar guitar) {
        this.id = guitar.getId();
        this.name = guitar.getName();
        this.price = guitar.getPrice();
        this.code = guitar.getCode();
        this.stock = guitar.getStock();
    }

    public Long getStock() {
        return stock;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }
}
