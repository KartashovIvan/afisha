package org.javaacademy.afisha.entity;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event {
    private Integer id;
    private String name;
    private Integer eventTypeId;
    private LocalDateTime eventDate;
    private Integer placeId;
}
