package com.co.starter.test.questions.user.create;

import com.co.starter.test.exceptions.DatabaseError;
import com.co.starter.test.models.database.user.dao.UserDAO;
import com.co.starter.test.models.database.user.dao.UserDaoJDBC;
import com.co.starter.test.models.database.user.dto.UserDTO;
import com.co.starter.test.models.datatables.user.CreateUserDataTable;
import com.co.starter.test.util.waits.WaitForCreatedUserInDb;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TheUserCreatedInDb implements Question<Boolean> {

    private final CreateUserDataTable data;
    private static final Logger LOGGER = Logger.getLogger(TheUserCreatedInDb.class.getName());
    private static final String NAME = "firstName";

    public TheUserCreatedInDb(CreateUserDataTable data) {this.data = data;}

    @Override
    public Boolean answeredBy(Actor actor) {
        String userName = actor.recall(NAME);

        waitForCreatedUser(actor, userName);

        try{
            UserDAO userDAO = new UserDaoJDBC();
            UserDTO userFromDb = userDAO.selectByName(userName);
            if(userFromDb == null){
                if (LOGGER.isLoggable(Level.WARNING)) {
                    LOGGER.log(Level.WARNING, String.format("User not found in data base with alias: %s", userName));
                }
                return false;
            }

            Serenity.recordReportData().withTitle("User record in database").andContents(userFromDb.toString());

            return validateUser(userFromDb, userName);

        } catch (SQLException e) {
            throw new DatabaseError(String.format("Error consulting in db: %s", e));
        }
    }

    private boolean validateUser(UserDTO user, String userName) {
        return user.getFirstName().equals(userName);

    }

    private void waitForCreatedUser(Actor actor, String userName) {
        if (this.data.getCodeResponse().equals("300")) {
            actor.attemptsTo(
                    WaitForCreatedUserInDb.wait(userName)
            );
        }
    }
    public static TheUserCreatedInDb isVerified(CreateUserDataTable data){
        return new TheUserCreatedInDb(data);
    }
}
