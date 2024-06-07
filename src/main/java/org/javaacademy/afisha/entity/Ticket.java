package org.javaacademy.afisha.entity;

import lombok.*;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ticket {
    private Integer id;
    private Integer eventId;
    private String clientEmail;
    private BigDecimal price;
    private Boolean isSelled;
}
