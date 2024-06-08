package org.javaacademy.afisha.controller.it;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.javaacademy.afisha.dto.EventDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

import static org.javaacademy.afisha.controller.util.AfishaUtil.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class EventControllerTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    public void clearTestRecord() {
        deleteTestEvent();
    }

    @Test
    @DisplayName("Тестирование успешного создание ивента")
    public void createEventSucces() {
        createTestPlace();
        RestAssured.given()
                .body(EVENT)
                .contentType(ContentType.JSON)
                .post(CREATE_EVENT_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Тестирование возврата существующеого ивента")
    public void createPlaceSucces() {
        createTestPlace();
        createTestEvent();
        List<EventDto> events = RestAssured.given()
                .get(TAKE_EVENT_URL)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .getList(".", EventDto.class);

        Assertions.assertTrue(events.contains(EVENT));
    }

    private void deleteTestEvent() {
        jdbcTemplate.execute("delete from application.ticket where event_id = (select id from application.event where name = '%s' and event_date = '%s')"
                .formatted(NAME_EVENT, EVENT_DATE));
        jdbcTemplate.execute("delete from application.event where name = '%s' and event_date = '%s'"
                .formatted(NAME_EVENT, EVENT_DATE));
        jdbcTemplate.execute("delete from application.place where name = '%s' and address = '%s' and city = '%s'"
                .formatted(NAME_PLACE, ADDRESS_PLACE, CITY_PLACE));
    }

    private void createTestPlace() {
        RestAssured.given()
                .body(PLACE)
                .contentType(ContentType.JSON)
                .post(CREATE_PLACE_URL);
    }

    private void createTestEvent() {
        RestAssured.given()
                .body(EVENT)
                .contentType(ContentType.JSON)
                .post(CREATE_EVENT_URL);
    }
}