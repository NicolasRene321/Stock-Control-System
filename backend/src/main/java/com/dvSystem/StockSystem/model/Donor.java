package com.dvSystem.StockSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "donors")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Donor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long codDonor;
    private String donorName;
    private String donorCpf;
    private String donorCnpj;
    private String donorEmail;
    private String donorAddress;
    private String donorSituation;
    private String donorPhone;

    @ManyToOne
    @JoinColumn(name = "employee_id",
                referencedColumnName = "codEmployee",
                nullable = false)
    private Employee employee;

    @OneToMany(mappedBy = "donor",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<Product> products;

}
