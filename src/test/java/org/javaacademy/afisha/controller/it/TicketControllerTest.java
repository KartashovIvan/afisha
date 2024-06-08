package org.javaacademy.afisha.controller.it;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.javaacademy.afisha.controller.util.AfishaUtil.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TicketControllerTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    public void clearTestRecord() {
        deleteTestEvent();
    }

    @Test
    @DisplayName("Успешная продажа билета")
    public void createPlaceSucces () {
        createTestPlace();
        createTestEvent();
        RestAssured.given()
                .body(TICKET)
                .contentType(ContentType.JSON)
                .patch(BUY_TICKET_URL)
                .then()
                .statusCode(HttpStatus.ACCEPTED.value());
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

    private void deleteTestEvent() {
        jdbcTemplate.execute("delete from application.ticket where event_id = (select id from application.event where name = '%s' and event_date = '%s')"
                .formatted(NAME_EVENT, EVENT_DATE));
        jdbcTemplate.execute("delete from application.event where name = '%s' and event_date = '%s'"
                .formatted(NAME_EVENT, EVENT_DATE));
        jdbcTemplate.execute("delete from application.place where name = '%s' and address = '%s' and city = '%s'"
                .formatted(NAME_PLACE, ADDRESS_PLACE, CITY_PLACE));
    }
}