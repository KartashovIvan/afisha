package org.javaacademy.afisha.controller.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.sql.SQLException;
import java.util.Objects;

@Configuration
@AllArgsConstructor
public class Config {
    private final JdbcTemplate jdbcTemplate;
    private final static String sqlScript = "src/test/resources/sql/init.sql";
    @PostConstruct
    public void init() throws SQLException {
        ScriptUtils.executeSqlScript(Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection(),
                new PathResource(sqlScript));
    }
}
