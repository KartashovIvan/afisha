package org.javaacademy.afisha.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReportDto {
    private String eventName;
    private String eventType;
    private BigDecimal soldAmount;
    private Integer ticketQty;
}
