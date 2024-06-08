package org.javaacademy.afisha.controller;

import org.javaacademy.afisha.dto.EventDto;
import org.javaacademy.afisha.dto.PlaceDto;
import org.javaacademy.afisha.dto.TicketDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AfishaTest {
    protected final static String CREATE_PLACE_URL = "/api/v1/place/add";
    protected final static String TAKE_PLACE_URL = "/api/v1/place/takeAll";
    protected final static String CREATE_EVENT_URL = "/api/v1/event/add";
    protected final static String TAKE_EVENT_URL = "/api/v1/event/takeAll";
    protected final static String BUY_TICKET_URL = "/api/v1/ticket/buy";

    protected final static String NAME_PLACE = "кинотеатр_test";
    protected final static String ADDRESS_PLACE = "пр. Мира, 28_test";
    protected final static String CITY_PLACE = "Тверь_test";
    protected final static PlaceDto PLACE = new PlaceDto(NAME_PLACE, ADDRESS_PLACE, CITY_PLACE);

    protected final static String NAME_EVENT = "Просмотр фильма_test";
    protected final static LocalDateTime EVENT_DATE = LocalDateTime.of(2024, 9, 14, 13, 0, 0);
    protected final static String EVENT_TYPE = "cinema";
    protected final static BigDecimal EVENT_PRICE = BigDecimal.valueOf(200.0);
    protected final static EventDto EVENT = initEVENT();

    protected final static String CLIENT_EMAIL = "test@mail";
    protected final static TicketDto TICKET = new TicketDto(NAME_EVENT, EVENT_DATE,CLIENT_EMAIL);

    private static EventDto initEVENT() {
        EventDto eventDto = new EventDto();
        eventDto.setName(NAME_EVENT);
        eventDto.setEventDate(EVENT_DATE);
        eventDto.setEventType(EVENT_TYPE);
        eventDto.setPrice(EVENT_PRICE);
        eventDto.setPlace(PLACE);
        return eventDto;
    }
}
