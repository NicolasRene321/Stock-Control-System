package com.dvSystem.StockSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long codProduct;
    private String productName;
    private String productDescription;
    private Long productAmount;
    private LocalDate productExpiration;
    private Double productPrice;

    @ManyToOne
    @JoinColumn(name = "donor_id",
                referencedColumnName = "codDonor",
                nullable = false)
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "employee_id",
                referencedColumnName = "codEmployee",
                nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id",
                referencedColumnName = "codManufacturer",
                nullable = false)
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "stock_id",
                referencedColumnName = "codStock",
                nullable = false)
    private Stock stock;
}
