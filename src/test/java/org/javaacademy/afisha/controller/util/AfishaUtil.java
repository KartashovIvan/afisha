package org.javaacademy.afisha.controller.util;

import lombok.experimental.UtilityClass;
import org.javaacademy.afisha.dto.EventDto;
import org.javaacademy.afisha.dto.PlaceDto;
import org.javaacademy.afisha.dto.ReportDto;
import org.javaacademy.afisha.dto.TicketDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@UtilityClass
public class AfishaUtil {
    public final static String CREATE_PLACE_URL = "/api/v1/place/add";
    public final static String TAKE_PLACE_URL = "/api/v1/place/takeAll";
    public final static String CREATE_EVENT_URL = "/api/v1/event/add";
    public final static String TAKE_EVENT_URL = "/api/v1/event/takeAll";
    public final static String BUY_TICKET_URL = "/api/v1/ticket/buy";
    public final static String REPORT_URL = "/api/v1/report";


    public final static String NAME_PLACE = "кинотеатр_test";
    public final static String ADDRESS_PLACE = "пр. Мира, 28_test";
    public final static String CITY_PLACE = "Тверь_test";
    public final static PlaceDto PLACE = new PlaceDto(NAME_PLACE, ADDRESS_PLACE, CITY_PLACE);

    public final static String NAME_EVENT = "Просмотр фильма_test";
    public final static LocalDateTime EVENT_DATE = LocalDateTime.of(2024, 9, 14, 13, 0, 0);
    public final static String EVENT_TYPE = "cinema";
    public final static BigDecimal EVENT_PRICE = BigDecimal.valueOf(200.0);
    public final static EventDto EVENT = initEVENT();

    public final static String CLIENT_EMAIL = "test@mail";
    public final static TicketDto TICKET = new TicketDto(NAME_EVENT, EVENT_DATE,CLIENT_EMAIL);

    public static List<ReportDto> listReports() {
        return List.of(new ReportDto("test_name_1","museum",BigDecimal.valueOf(100.0), 1),
                new ReportDto("test_name_2", "cinema", BigDecimal.valueOf(200.0),1),
                new ReportDto("test_name_3", "theatre", BigDecimal.valueOf(300.0),1));
    }

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
