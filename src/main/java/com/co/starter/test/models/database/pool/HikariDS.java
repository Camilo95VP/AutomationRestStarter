package com.co.starter.test.models.database.pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariDS {

    private static final EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

    private static final String MASTER_DB_NAME = "master";
    private static HikariDataSource userDataSource;

    static {
        HikariDS.userDataSource = null;
        try {
            HikariDS.userDataSource = setDataSource(MASTER_DB_NAME);
        } catch (NullPointerException e){
            throw new IllegalArgumentException("Something went wrong in conection with db");
        }
    }
    private HikariDS() {
    }

    private static  HikariDataSource setDataSource(String dbName){
        String userHostNameSql = EnvironmentSpecificConfiguration.from(HikariDS.environmentVariables).getProperty("constants.database.user_host_name_sql");
        String userDataBaseName = "user";
        String userIdSql = EnvironmentSpecificConfiguration.from(HikariDS.environmentVariables).getProperty("constants.database.user_id_sql");
        String userPasswordSql = EnvironmentSpecificConfiguration.from(HikariDS.environmentVariables).getProperty("constants.database.user_password_sql");

        String dbUrl = String.format("jdbc:sqlserver://%s:1433;databaseName=%s;encrypt=false;trustServerCertificate=true", userHostNameSql, userDataBaseName);

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(dbUrl);
        hikariConfig.setUsername(userIdSql);
        hikariConfig.setPassword(userPasswordSql);
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(hikariConfig);
    }

    public static Connection getUserConnection() throws SQLException {
        return  HikariDS.userDataSource.getConnection();
    }
}
