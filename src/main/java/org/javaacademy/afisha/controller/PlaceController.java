package org.javaacademy.afisha.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.javaacademy.afisha.dto.EventDto;
import org.javaacademy.afisha.dto.PlaceDto;
import org.javaacademy.afisha.entity.Ticket;
import org.javaacademy.afisha.servcie.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/place")
@Tag(name = "Места мероприятий" , description = "Операции с местами проведения мероприятий")
public class PlaceController {
    private PlaceService placeService;

    @PostMapping("/add")
    @Operation(summary = "Создание нового место проведения")
    public ResponseEntity<?> addNewPlace(@RequestBody PlaceDto placeDto) {
        placeService.addNewPlace(placeDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/takeAll")
    @Operation(summary = "Вымести все места мероприятий")
    public ResponseEntity<List<PlaceDto>> takeAllPlace() {
        return ResponseEntity.ok(placeService.takeAllPlace());
    }
}
