package com.dvSystem.StockSystem.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ManufacturerDTO {
    private Long codManufacturer;
    private String nameManufacturer;
    private Long employeeID;
}
