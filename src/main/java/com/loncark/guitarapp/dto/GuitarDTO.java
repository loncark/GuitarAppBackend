package com.loncark.guitarapp.dto;

import com.loncark.guitarapp.model.Guitar;

import java.math.BigDecimal;

public class GuitarDTO {
    Long id;
    String name;
    BigDecimal price;
    Long code;

    public GuitarDTO(Long code, Guitar guitar) {
        this.id = guitar.getId();
        this.name = guitar.getName();
        this.price = guitar.getPrice();
        this.code = code;
    }

    public GuitarDTO(Guitar guitar) {
        this.id = guitar.getId();
        this.name = guitar.getName();
        this.price = guitar.getPrice();
        this.code = guitar.getCode();
    }

    public GuitarDTO() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getCode() {
        return code;
    }
}
