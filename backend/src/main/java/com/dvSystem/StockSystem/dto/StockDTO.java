package com.dvSystem.StockSystem.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StockDTO {
    private Long codStock;
    private Long productCod;
    private String productName;
    private Long productAmount;
}
