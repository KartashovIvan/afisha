package org.javaacademy.afisha.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class PlaceDto {
    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private String city;
}
