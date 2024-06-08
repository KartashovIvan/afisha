package org.javaacademy.afisha.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.javaacademy.afisha.dto.EventDto;
import org.javaacademy.afisha.servcie.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/event")
@Tag(name = "Мероприятия" , description = "Операции с мероприятиями")
public class EventController {
    private EventService eventService;

    @PostMapping("/add")
    @Operation(summary = "Создание нового события")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createEvent(@RequestBody EventDto eventDto) {
        eventService.createEvent(eventDto);
    }

    @GetMapping("/takeAll")
    @Operation(summary = "Просмотр всех событий")
    public List<EventDto> takeAllEvents() {
        return eventService.takeAllEvents();
    }
}
