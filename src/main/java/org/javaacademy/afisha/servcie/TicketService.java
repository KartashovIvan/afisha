package org.javaacademy.afisha.servcie;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.afisha.dto.TicketDto;
import org.javaacademy.afisha.entity.Event;
import org.javaacademy.afisha.repository.EventRepository;
import org.javaacademy.afisha.repository.EventTypeRepository;
import org.javaacademy.afisha.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TicketService {
    private static final String NOT_EVENT_TYPE = "museum";
    private TicketRepository ticketRepository;
    private EventRepository eventRepository;
    private EventTypeRepository eventTypeRepository;

    public void buyTicket(TicketDto ticketDto) {
        Event event = eventRepository.taleEventByNameAndDate(ticketDto.getEventName(), ticketDto.getEventDate());
        String nameEventType = eventTypeRepository.getNameEventTypeById(event.getEventTypeId());

        if (!nameEventType.equals(NOT_EVENT_TYPE)) {
            sellTicket(event.getId(), ticketDto.getClientEmail());
        } else {
            sellTicketToMuseum(event.getId(), ticketDto.getClientEmail());
        }
        log.info("Билет куплен");
    }

    private void sellTicket(Integer eventId, String clientEmail) {
        if (ticketRepository.checkNotSelledTickets(eventId) != 0) {
            ticketRepository.buyTicket(eventId, clientEmail);
        } else {
            throw new RuntimeException("All ticket selled");
        }
    }

    private void sellTicketToMuseum(Integer eventId, String clientEmail) {
        ticketRepository.buyTicketToMuseum(eventId, clientEmail);
    }
}
