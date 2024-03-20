package com.co.starter.test.util.waits;

import com.co.starter.test.models.database.user.dao.UserDaoJDBC;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.Wait;

import java.time.Duration;

public class WaitForCreatedUserInDb {
    private WaitForCreatedUserInDb() {
        throw new IllegalStateException("Utility class");
    }

    public static Performable wait(String name) {
        return new Task() {
            final UserDaoJDBC userDao = new UserDaoJDBC();

            @Override
            public <T extends Actor> void performAs(T actor) {
                actor.attemptsTo(
                        Wait.until(
                                () -> userDao.selectByName(name) != null
                        ).forNoMoreThan(Duration.ofMillis(6000))
                );
            }
        };
    }
}
