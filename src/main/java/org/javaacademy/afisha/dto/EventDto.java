package org.javaacademy.afisha.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EventDto {
    private String name;
    private LocalDateTime eventDate;
    private String eventType;
    private BigDecimal price;
    private PlaceDto place;
}
