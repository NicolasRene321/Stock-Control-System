package com.dvSystem.StockSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "manufacturers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long codManufacturer;
    private String nameManufacturer;

    @ManyToOne
    @JoinColumn(name = "employee_id",
                referencedColumnName = "codEmployee",
                nullable = false)
    private Employee employee;

    @OneToMany(mappedBy = "manufacturer",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<Product> products;
}