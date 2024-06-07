package org.javaacademy.afisha.controller;

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
@RequestMapping("/place")
public class PlaceController {
    private PlaceService placeService;

    @PostMapping("/add")
    public ResponseEntity<?> addNewPlace(@RequestBody PlaceDto placeDto) {
        placeService.addNewPlace(placeDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/takeAll")
    public ResponseEntity<List<PlaceDto>> takeAllPlace() {
        return ResponseEntity.ok(placeService.takeAllPlace());
    }

    @GetMapping("/test")
    public EventDto init() {
        EventDto eventDto = new EventDto();
        eventDto.setEventDate(LocalDateTime.now());
        eventDto.setPlace(new PlaceDto("null","null","null"));
        return eventDto;
    }
}
