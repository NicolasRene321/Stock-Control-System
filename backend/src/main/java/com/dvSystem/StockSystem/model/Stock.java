package com.dvSystem.StockSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "stocks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long codStock;
    @Column(unique = true)
    private Long productCod;
    private String productName;
    private Long productAmount;

    @OneToMany(mappedBy = "stock",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<Product> products;

    @OneToMany(mappedBy = "stock",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<ProductDeliver> deliveries;
}
