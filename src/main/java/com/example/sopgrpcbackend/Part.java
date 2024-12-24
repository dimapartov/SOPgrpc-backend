package com.example.sopgrpcbackend;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Table(name = "parts")
public class Part {

    @Id
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "quantity_on_storage", nullable = false)
    private int quantityOnStorage;

    @Column(nullable = false)
    private BigDecimal price;

    public Part() {
    }

    public Part(UUID id, String name, int quantityOnStorage, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.quantityOnStorage = quantityOnStorage;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantityOnStorage() {
        return quantityOnStorage;
    }

    public void setQuantityOnStorage(int quantityOnStorage) {
        this.quantityOnStorage = quantityOnStorage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}