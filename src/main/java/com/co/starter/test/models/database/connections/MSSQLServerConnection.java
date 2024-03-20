package com.co.starter.test.models.database.connections;

import com.co.starter.test.models.database.pool.HikariDS;

import java.sql.Connection;
import java.sql.SQLException;

public class MSSQLServerConnection {
    private MSSQLServerConnection() {
    }

    public static Connection getUserConnection() throws SQLException {
        return HikariDS.getUserConnection();
    }

    public static void close(Connection conn) throws SQLException {
        conn.close();
    }
}
