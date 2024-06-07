package org.javaacademy.afisha.repository;


import lombok.AllArgsConstructor;
import org.javaacademy.afisha.entity.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.util.List;

@Component
@AllArgsConstructor
public class EventRepository {
    private JdbcTemplate jdbcTemplate;

    public void addNewEvent(Event event) {
        String sqlAddNewEvent = """
                insert into application.event (name, event_type_id, event_date, place_id)
                values (?,?,?,?)
                """;
        jdbcTemplate.update(sqlAddNewEvent, preparedStatement ->{
            preparedStatement.setString(1,event.getName());
            preparedStatement.setInt(2,event.getEventTypeId());
            preparedStatement.setTimestamp(3,Timestamp.valueOf(event.getEventDate()));
            preparedStatement.setInt(4,event.getPlaceId());
        });
    }

    public Event getEventById(String id) {
        String sqlEventById = "select * from application.event where id = " + id;
        return jdbcTemplate.queryForObject(sqlEventById, (resultSet, rowNum) -> {
            Event event = new Event();
            event.setId(resultSet.getInt("id"));
            event.setName(resultSet.getString("name"));
            event.setEventTypeId(resultSet.getInt("event_type_id"));
            event.setEventDate(resultSet.getTimestamp("event_date").toLocalDateTime());
            event.setPlaceId(resultSet.getInt("place_id"));
            return event;
        });
    }

    public List<Event> getAllEvent() {
        return jdbcTemplate.query("select * from application.event", (resultSet, rowNumber) -> {
            Event event = new Event();
            event.setId(resultSet.getInt("id"));
            event.setName(resultSet.getString("name"));
            event.setEventTypeId(resultSet.getInt("event_type_id"));
            event.setEventDate(resultSet.getTimestamp("event_date").toLocalDateTime());
            event.setPlaceId(resultSet.getInt("place_id"));
            return event;
        });
    }
}