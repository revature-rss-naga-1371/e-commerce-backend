package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private double price;
    private String description;
    private String image;
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    public Product(int id, int quantity, double price, String description, String image, String name) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.image = image;
        this.name = name;
    }
}
