package com.loncark.guitarapp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Guitar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long code;

    @Enumerated(EnumType.STRING)
    Material body;
    @Enumerated(EnumType.STRING)
    Material neck;
    String name;
    BigDecimal price;
    Long stock;

    @Override
    public String toString() {
        return "Guitar{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                '}';
    }

    public Guitar(String brand, Material body, Material neck, String name, BigDecimal price, Long stock, Long code) {
        this.body = body;
        this.neck = neck;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.code = code;
    }

    public Guitar() {}

    public Long getId() {
        return id;
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

    public Long getCode() {
        return code;
    }

}
