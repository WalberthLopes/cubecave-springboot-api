package com.cubecave.api.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class LoadDatabase {
    @Value("${spring.datasource.url}")
    private String springDatasourceUrl;

    public Connection connect() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(springDatasourceUrl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
}
