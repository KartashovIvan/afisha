package org.javaacademy.afisha.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Place {
    private Integer id;
    private String name;
    private String address;
    private String city;
}
