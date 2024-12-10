package com.dvSystem.StockSystem.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDTO {
    private Long codEmployee;
    private String employeeName;
    private String employeeCpf;
    private String employeePosition;
    private String employeeSituation;
    private String employeePhone;
}
