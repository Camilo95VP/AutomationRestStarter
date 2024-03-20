package com.co.starter.test.models.database.user.dao;

import com.co.starter.test.models.database.connections.MSSQLServerConnection;
import com.co.starter.test.models.database.user.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJDBC implements UserDAO {

    private static final String SQL_SELECT_BY_NAME = "SELECT TOP 1 * FROM users WHERE first_name = ? ORDER BY id DESC";

    private Connection transactionalConection;

    public UserDaoJDBC(Connection transactionalConection) {this.transactionalConection = transactionalConection;}

    public UserDaoJDBC() {
    }

    @Override
    public List<UserDTO> selectAllByName(String user) throws SQLException {
        return null;
    }

    @Override
    public UserDTO selectByName(String name) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        try{
            conn = this.transactionalConection != null ? this.transactionalConection : MSSQLServerConnection.getUserConnection();
            try (PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_NAME)){
                ps.setString(1,name);
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    user = UserDTO.builder()
                            .userId(rs.getInt("id"))
                            .address(rs.getString("address"))
                            .city(rs.getString("city"))
                            .email(rs.getString("email"))
                            .firstName(rs.getString("first_name"))
                            .lastName(rs.getString("last_name"))
                            .telephone(rs.getString("telephone"))
                            .build();
                }
            }
        } finally {
            if (this.transactionalConection == null && (conn != null)) {
                MSSQLServerConnection.close(conn);
            }
        }
        return user;
    }

    @Override
    public int delete(UserDTO user) throws SQLException {
        return 0;
    }
}
