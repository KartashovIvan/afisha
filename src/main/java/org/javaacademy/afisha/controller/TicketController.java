package org.javaacademy.afisha.controller;

import lombok.AllArgsConstructor;
import org.javaacademy.afisha.dto.TicketDto;
import org.javaacademy.afisha.servcie.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("/ticket")
public class TicketController {
    private TicketService ticketService;

    @PostMapping("/buy")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void buyTicket(@RequestBody TicketDto ticketDto) {
        ticketService.buyTicket(ticketDto);
    }

    @GetMapping("/test")
    public TicketDto init() {
        return new TicketDto(" ", LocalDateTime.now(), " ");
    }
}
