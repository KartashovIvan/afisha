package org.javaacademy.afisha.repository;

import lombok.AllArgsConstructor;
import org.javaacademy.afisha.entity.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@AllArgsConstructor
public class TicketRepository {
    private JdbcTemplate jdbcTemplate;

    public void addNewTicket(Ticket ticket) {
        String sqlAddNewEvent = """
                insert into application.ticket (event_id, client_email, price) 
                values (?,?,?);
                """;
        jdbcTemplate.update(sqlAddNewEvent, preparedStatement -> {
            preparedStatement.setInt(1, ticket.getEventId());
            preparedStatement.setString(2, ticket.getClientEmail());
            preparedStatement.setBigDecimal(3, ticket.getPrice());
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
}
