package com.ke5eo.Datastore.SQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static Connection connect(String dsn) {
        Connection conn;
        try {
            conn = DriverManager.getConnection(dsn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
