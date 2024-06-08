package org.javaacademy.afisha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {
    private String eventName;
    private String eventType;
    private BigDecimal soldAmount;
    private Integer ticketQty;
}
