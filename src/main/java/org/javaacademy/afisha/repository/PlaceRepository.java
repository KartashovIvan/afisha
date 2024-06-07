package org.javaacademy.afisha.repository;

import lombok.AllArgsConstructor;
import org.javaacademy.afisha.entity.Place;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@AllArgsConstructor
public class PlaceRepository {
    private JdbcTemplate jdbcTemplate;

    public void addNewPlace(Place place) {
        String sqlAddNewEvent = """
                insert into application.place (name, address, city)
                values (?,?,?)
                """;
        jdbcTemplate.update(sqlAddNewEvent, preparedStatement -> {
            preparedStatement.setString(1, place.getName());
            preparedStatement.setString(2, place.getAddress());
            preparedStatement.setString(3, place.getCity());
        });
    }

    public Place getPlaceById(String id) {
        String sqlEventById = "select * from application.place where id = " + id;
        return jdbcTemplate.queryForObject(sqlEventById, (resultSet, rowNum) -> {
            Place place = new Place();
            place.setId(resultSet.getInt("id"));
            place.setName(resultSet.getString("name"));
            place.setAddress(resultSet.getString("address"));
            place.setCity(resultSet.getString("city"));
            return place;
        });
    }

    public List<Place> getAllPlace() {
        return jdbcTemplate.query("select * from application.place", (resultSet, rowNumber) -> {
            Place place = new Place();
            place.setId(resultSet.getInt("id"));
            place.setName(resultSet.getString("name"));
            place.setAddress(resultSet.getString("address"));
            place.setCity(resultSet.getString("city"));
            return place;
        });
    }
}
