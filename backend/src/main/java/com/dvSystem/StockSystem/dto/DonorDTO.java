package com.dvSystem.StockSystem.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DonorDTO {
    private Long codDonor;
    private String donorName;
    private String donorCpf;
    private String donorCnpj;
    private String donorEmail;
    private String donorAddress;
    private String donorSituation;
    private String donorPhone;
    private Long employeeId;
}
