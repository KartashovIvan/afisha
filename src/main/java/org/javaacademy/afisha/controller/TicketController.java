package org.javaacademy.afisha.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.javaacademy.afisha.dto.TicketDto;
import org.javaacademy.afisha.servcie.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/ticket")
@Tag(name = "Билеты" , description = "Операции с билетами")
public class TicketController {
    private TicketService ticketService;

    @PatchMapping("/buy")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Покупка билеа на мероприятие")
    public void buyTicket(@RequestBody TicketDto ticketDto) {
        ticketService.buyTicket(ticketDto);
    }
}
