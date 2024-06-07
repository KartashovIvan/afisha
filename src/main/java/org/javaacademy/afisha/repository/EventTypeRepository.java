package org.javaacademy.afisha.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventTypeRepository {
    private JdbcTemplate jdbcTemplate;

    public Integer getIdEventTypeByName(String eventType) {
        String sqlGetIdByName = "select id from application.event_type where name = ?";
        return jdbcTemplate.query(sqlGetIdByName,
                        preparedStatement -> preparedStatement.setString(1, eventType),
                        (resultSet, rowNum) -> resultSet.getInt("id")).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No such event type: " + eventType));
    }

    public String getNameEventTypeById(Integer id) {
        String sqlGetNameById = "select name from application.event_type where id = ?";
        return jdbcTemplate.query(sqlGetNameById,
                        preparedStatement -> preparedStatement.setInt(1, id),
                        (resultSet, rowNum) -> resultSet.getString("name")).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No such event type with id: " + id));
    }
}
