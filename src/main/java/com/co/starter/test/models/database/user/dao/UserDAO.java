package com.co.starter.test.models.database.user.dao;


import com.co.starter.test.models.database.user.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List<UserDTO> selectAllByName(String user) throws SQLException;
    UserDTO selectByName(String name) throws SQLException;
    int delete(UserDTO user) throws SQLException;

}
