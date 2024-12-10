package com.dvSystem.StockSystem.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDeliverDTO {
    private Long codDeliver;
    private LocalDate deliverDate;
    private String productDeliverName;
    private Long deliverAmount;
    private String deliverResponsible;
    private Long stockId;
    private Long employeeId;
}
