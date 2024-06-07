package org.javaacademy.afisha.controller;

import lombok.AllArgsConstructor;
import org.javaacademy.afisha.dto.EventDto;
import org.javaacademy.afisha.servcie.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/event")
public class EventController {
    private EventService eventService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createEvent(@RequestBody EventDto eventDto) {
        eventService.createEvent(eventDto);
    }

    @GetMapping("/takeAll")
    public List<EventDto> takeAllEvents() {
        return eventService.takeAllEvents();
    }
}
