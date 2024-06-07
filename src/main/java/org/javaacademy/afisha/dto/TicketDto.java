package org.javaacademy.afisha.dto;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class TicketDto {
    @NonNull
    private String eventName;
    @NonNull
    private LocalDateTime eventDate;
    @NonNull
    private String clientEmail;
}
