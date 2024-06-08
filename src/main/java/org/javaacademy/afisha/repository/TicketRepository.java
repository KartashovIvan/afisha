package org.javaacademy.afisha.repository;

import lombok.AllArgsConstructor;
import org.javaacademy.afisha.entity.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class TicketRepository {
    private JdbcTemplate jdbcTemplate;

    public void addNewTicket(Ticket ticket) {
        String sqlAddNewEvent = """
                insert into application.ticket (event_id, price)
                values (?,?)
                """;
        jdbcTemplate.update(sqlAddNewEvent, preparedStatement -> {
            preparedStatement.setInt(1, ticket.getEventId());
            preparedStatement.setBigDecimal(2, ticket.getPrice());
        });
    }

    public Ticket getTicketById(String id) {
        String sqlEventById = "select * from application.ticket where id = " + id;
        return jdbcTemplate.queryForObject(sqlEventById, (resultSet, rowNum) -> {
            Ticket ticket = new Ticket();
            ticket.setId(resultSet.getInt("id"));
            ticket.setEventId(resultSet.getInt("event_id"));
            ticket.setClientEmail(resultSet.getString("client_email"));
            ticket.setPrice(resultSet.getBigDecimal("price"));
            ticket.setIsSelled(resultSet.getBoolean("is_selled"));
            return ticket;
        });
    }

    public List<Ticket> getAllTicket() {
        return jdbcTemplate.query("select * from application.ticket", (resultSet, rowNumber) -> {
            Ticket ticket = new Ticket();
            ticket.setId(resultSet.getInt("id"));
            ticket.setEventId(resultSet.getInt("event_id"));
            ticket.setClientEmail(resultSet.getString("client_email"));
            ticket.setPrice(resultSet.getBigDecimal("price"));
            ticket.setIsSelled(resultSet.getBoolean("is_selled"));
            return ticket;
        });
    }

    public BigDecimal takePriceByEventId(Integer eventId) {
        String sqlPriceByEventId = "select price from application.ticket where event_id = ?";
        return jdbcTemplate.query(sqlPriceByEventId,
                        preparedStatement -> preparedStatement.setInt(1, eventId),
                        (resultSet, rowNum) -> resultSet.getBigDecimal("price")).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No such ticket with event id: " + eventId));
    }

    public int checkNotSelledTickets(Integer idEvent) {
        String sqlTakeNotSelledTicket = """
                select count(*) from application.ticket
                where event_id = %s and client_email isnull;
                """.formatted(idEvent);

        return Objects.requireNonNull(jdbcTemplate.queryForObject(sqlTakeNotSelledTicket,
                        (resultSet, rowNum) -> resultSet.getInt("count")))
                .describeConstable()
                .orElse(0);
    }

    public void buyTicket(Integer eventId, String clientEmail) {
        String sqlUpdateTicket = """
                update application.ticket set client_email = ?, is_selled = true
                where id = (select id from application.ticket where event_id = ? and client_email isnull limit 1);
                                """;
        jdbcTemplate.update(sqlUpdateTicket, preparedStatement -> {
            preparedStatement.setString(1, clientEmail);
            preparedStatement.setInt(2, eventId);
        });
    }

    public void buyTicketToMuseum(Integer eventId, String clientEmail) {
        String sqlAddNewEvent = """
                insert into application.ticket (event_id, client_email, price, is_selled)
                values (?,?,?,?)
                """;
        jdbcTemplate.update(sqlAddNewEvent, preparedStatement -> {
            preparedStatement.setInt(1, eventId);
            preparedStatement.setString(2, clientEmail);
            preparedStatement.setBigDecimal(3,BigDecimal.valueOf(100));
            preparedStatement.setBoolean(4,true);
        });
    }
}
