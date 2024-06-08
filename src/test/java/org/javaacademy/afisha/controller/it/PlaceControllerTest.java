package org.javaacademy.afisha.controller.it;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.javaacademy.afisha.dto.PlaceDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

import static org.javaacademy.afisha.controller.util.AfishaUtil.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PlaceControllerTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    public void init() {
        deleteTestPlace();
    }

    @Test
    @DisplayName("Тестирование успешного создание места")
    public void createPlaceSucces () {
        RestAssured.given()
                .body(PLACE)
                .contentType(ContentType.JSON)
                .post(CREATE_PLACE_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Тестирование создание существующего места")
    public void createExistPlaceFail () {
        createTestPlace();
        RestAssured.given()
                .body(PLACE)
                .contentType(ContentType.JSON)
                .post(CREATE_PLACE_URL)
                .then()
                .statusCode(HttpStatus.NOT_ACCEPTABLE.value());
    }

    @Test
    @DisplayName("Тестирование возврата существующеого места")
    public void checkExistPlaceSucces () {
        createTestPlace();
        List<PlaceDto> places = RestAssured.given()
                .get(TAKE_PLACE_URL)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .getList(".", PlaceDto.class);

        Assertions.assertTrue(places.contains(PLACE));
    }

    private void createTestPlace() {
        RestAssured.given()
                .body(PLACE)
                .contentType(ContentType.JSON)
                .post(CREATE_PLACE_URL);
    }

    private void deleteTestPlace() {
        jdbcTemplate.execute("delete from application.place where name = '%s' and address = '%s' and city = '%s'"
                .formatted(NAME_PLACE, ADDRESS_PLACE, CITY_PLACE));
    }
}