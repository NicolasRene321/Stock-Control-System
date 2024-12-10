package com.dvSystem.StockSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "deliveries")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDeliver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long codDeliver;
    private LocalDate deliverDate;
    private String productDeliverName;
    private Long deliverAmount;
    private String deliverResponsible;

    @ManyToOne
    @JoinColumn(name = "employee_id",
                referencedColumnName = "codEmployee",
                nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "stock_id",
                referencedColumnName = "codStock",
                nullable = false)
    private Stock stock;
}
