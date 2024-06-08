package org.javaacademy.afisha.repository;

import lombok.AllArgsConstructor;
import org.javaacademy.afisha.dto.ReportDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ReportRepository {
    private JdbcTemplate jdbcTemplate;

    public List<ReportDto> takeReport() {
        String sqlReport = """
                SELECT
                  e.name as "event_name",
                  et.name as "event_type",
                  COALESCE(r.sum, 0) AS "profit",
                  COALESCE(r.count, 0) AS "tickets"
                FROM
                  application.event e
                  LEFT JOIN application.event_type et ON e.event_type_id = et.id
                  LEFT JOIN (
                    SELECT
                      event_id,
                      SUM(price),
                      COUNT(*)
                    FROM
                      application.ticket
                    WHERE
                      client_email notnull  and is_selled = TRUE
                    GROUP BY
                      event_id
                  ) r ON e.id = r.event_id;
                """;

        return jdbcTemplate.query(sqlReport , (resultSet, rowNumber) ->{
            ReportDto reportDto = new ReportDto();
            reportDto.setEventName(resultSet.getString("event_name"));
            reportDto.setEventType(resultSet.getString("event_type"));
            reportDto.setSoldAmount(resultSet.getBigDecimal("profit"));
            reportDto.setTicketQty(resultSet.getInt("tickets"));
            return reportDto;
        });
    }
}
