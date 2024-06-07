package org.javaacademy.afisha.servcie;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.PathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Objects;

@Service
@Profile("first")
@RequiredArgsConstructor
public class InitDatabaseService {
    private final JdbcTemplate jdbcTemplate;
    private final static String sqlScript = "src/main/resources/sql/init.sql";

    @PostConstruct
    public void init() throws SQLException {
        ScriptUtils.executeSqlScript(Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection(), new PathResource(sqlScript));
    }
}
