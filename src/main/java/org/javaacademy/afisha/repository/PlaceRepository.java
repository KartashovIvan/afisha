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
        if (!checkExistsPlace(place).isEmpty()) {
            throw new RuntimeException("Place already exists");
        }
        jdbcTemplate.update(sqlAddNewEvent, preparedStatement -> {
            preparedStatement.setString(1, place.getName());
            preparedStatement.setString(2, place.getAddress());
            preparedStatement.setString(3, place.getCity());
        });
    }

    public List<Place> checkExistsPlace(Place place) {
        String sqlCheckExistPlace = "select * from application.place where name = ? and address = ? and city = ?";
        return jdbcTemplate.query(sqlCheckExistPlace, preparedStatement -> {
                    preparedStatement.setString(1, place.getName());
                    preparedStatement.setString(2, place.getAddress());
                    preparedStatement.setString(3, place.getCity());
                },
                (resultSet, rowNum) -> {
                    Place existPlace = new Place();
                    existPlace.setId(resultSet.getInt("id"));
                    existPlace.setName(resultSet.getString("name"));
                    existPlace.setAddress(resultSet.getString("address"));
                    existPlace.setCity(resultSet.getString("city"));
                    return existPlace;
                });
    }

    public Place getPlaceById(Integer id) {
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
