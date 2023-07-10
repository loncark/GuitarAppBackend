package com.loncark.guitarapp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Guitar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String code;

    @Enumerated(EnumType.STRING)
    Material body;
    @Enumerated(EnumType.STRING)
    Material neck;
    String name;
    BigDecimal price;
    Long stock;

    public Guitar(long id, Material body, Material neck, String name, BigDecimal price, long stock, String code) {
        this.id = id;
        this.body = body;
        this.neck = neck;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Guitar{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                '}';
    }

    public Guitar(Material body, Material neck, String name, BigDecimal price, Long stock, String code) {
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

    public String getCode() {
        return code;
    }

    public void setId(long newid) { id = newid; }
}
