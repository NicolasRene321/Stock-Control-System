package com.dvSystem.StockSystem.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO {
    private Long codProduct;
    private String productName;
    private String productDescription;
    private Long productAmount;
    private LocalDate productExpiration;
    private Double productPrice;
    private Long donorId;
    private Long stockId;
    private Long manufacturerId;
    private Long employeeId;
}
