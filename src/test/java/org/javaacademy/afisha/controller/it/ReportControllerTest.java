package org.javaacademy.afisha.controller.it;

import io.restassured.RestAssured;
import org.javaacademy.afisha.dto.ReportDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.javaacademy.afisha.controller.util.AfishaUtil.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ReportControllerTest {
    @Test
    @DisplayName("Проверка на корректный отчет")
    public void checkCorrectReport () {
        List<ReportDto> reports = RestAssured.given()
                .get(REPORT_URL)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .getList(".", ReportDto.class);

        assertTrue(reports.containsAll(listReports()));
    }
}