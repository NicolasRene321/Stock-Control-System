package com.dvSystem.StockSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long codEmployee;
    private String employeeName;
    private String employeeCpf;
    private String employeePosition;
    private String employeeSituation;
    private String employeePhone;

    @OneToMany(mappedBy = "employee",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<Donor> donors;

    @OneToMany(mappedBy = "employee",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<ProductDeliver> delivers;

    @OneToMany(mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Product> products;

    @OneToMany(mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Manufacturer> manufacturers;
}
