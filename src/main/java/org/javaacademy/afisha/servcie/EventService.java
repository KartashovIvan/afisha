package org.javaacademy.afisha.servcie;

import lombok.AllArgsConstructor;
import org.javaacademy.afisha.dto.EventDto;
import org.javaacademy.afisha.entity.Event;
import org.javaacademy.afisha.entity.Place;
import org.javaacademy.afisha.repository.EventRepository;
import org.javaacademy.afisha.repository.EventTypeRepository;
import org.javaacademy.afisha.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    private EventRepository eventRepository;
    private PlaceService placeService;
    private EventTypeRepository eventTypeRepository;
    private TicketRepository ticketRepository;

    public void createEvent(EventDto event) {
        Place place = placeService.takePlace(event.getPlace());
        Integer idEventType = eventTypeRepository.getIdEventTypeByName(event.getEventType());
        int countTickets = count(event.getEventType());
        eventRepository.addNewEvent(
                new Event(null,
                        event.getName(),
                        idEventType,
                        event.getEventDate(),
                        place.getId()), event.getPrice(), countTickets);
    }

    public List<EventDto> takeAllEvents () {
        List<Event> allEvent = eventRepository.getAllEvent();
        return convertToEventDto(allEvent);
    }

    private List<EventDto> convertToEventDto(List<Event> allEvent) {
        return allEvent.stream().map(event -> {
            EventDto eventDto = new EventDto();
            eventDto.setName(event.getName());
            eventDto.setEventDate(event.getEventDate());
            eventDto.setEventType(eventTypeRepository.getNameEventTypeById(event.getEventTypeId()));
            eventDto.setPrice(ticketRepository.takePriceByEventId(event.getId()));
            eventDto.setPlace(placeService.getPlaceById(event.getPlaceId()));
            return eventDto;
        }).toList();
    }

    private int count(String eventTypeName) {
        return switch (eventTypeName) {
            case "cinema" -> 100;
            case "theatre" -> 50;
            default -> 0;
        };
    }
}
