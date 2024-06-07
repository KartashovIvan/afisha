package org.javaacademy.afisha.repository;

import lombok.AllArgsConstructor;
import org.javaacademy.afisha.entity.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class EventRepository {
    private JdbcTemplate jdbcTemplate;
    private TransactionTemplate transactionTemplate;

    public void addNewEvent(Event event, BigDecimal price, int count) {
        String sqlAddNewEvent = """
                insert into application.event (name, event_type_id, event_date, place_id)
                values (?,?,?,?)
                """;
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            jdbcTemplate.update(sqlAddNewEvent, preparedStatement -> {
                preparedStatement.setString(1, event.getName());
                preparedStatement.setInt(2, event.getEventTypeId());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(event.getEventDate()));
                preparedStatement.setInt(4, event.getPlaceId());
            });
            Integer idNewEvent = takeIdNewEvent(event);
            createTicket(idNewEvent, price, count);
        });
    }

    public Event getEventById(String id) {
        String sqlEventById = "select * from application.event where id = ?";
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


    public Event taleEventByNameAndDate(String eventName, LocalDateTime eventDate) {
        String sqlTakeEventByNameAndDate = """
                select * from application.event
                where name = ? and event_date = ?
                """;
        return jdbcTemplate.query(sqlTakeEventByNameAndDate, preparedStatement -> {
                            preparedStatement.setString(1, eventName);
                            preparedStatement.setTimestamp(2, Timestamp.valueOf(eventDate));
                        },
                        (resultSet, rowNum) -> {
                            Event event = new Event();
                            event.setId(resultSet.getInt("id"));
                            event.setName(resultSet.getString("name"));
                            event.setEventTypeId(resultSet.getInt("event_type_id"));
                            event.setEventDate(resultSet.getTimestamp("event_date").toLocalDateTime());
                            event.setPlaceId(resultSet.getInt("place_id"));
                            return event;
                        }).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No event %s in date %s".formatted(eventName, eventDate)));
    }

    private Integer takeIdNewEvent(Event event) {
        String sqlGetIdNewEvent = """
                select id from application.event
                where name = '%s' and event_type_id = %s and event_date = '%s' and place_id = %s
                """.formatted(event.getName(),
                event.getEventTypeId(),
                Timestamp.valueOf(event.getEventDate()),
                event.getPlaceId());

        return jdbcTemplate.queryForObject(sqlGetIdNewEvent, (resultSet, rowNum) -> resultSet.getInt("id"));
    }

    private void createTicket(Integer idNewEvent, BigDecimal price, int count) {
        String sqlAddTicket = """
                insert into application.ticket (event_id, price)
                values (?,?)
                """;

        while (count > 0) {
            jdbcTemplate.update(sqlAddTicket, preparedStatement -> {
                preparedStatement.setInt(1, idNewEvent);
                preparedStatement.setBigDecimal(2, price);
            });
            count--;
        }
    }
}