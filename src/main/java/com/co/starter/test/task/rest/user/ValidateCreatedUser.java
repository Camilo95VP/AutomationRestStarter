package com.co.starter.test.task.rest.user;

import com.co.starter.test.models.datatables.user.CreateUserDataTable;
import com.co.starter.test.questions.user.create.TheUserCreatedInDb;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.*;

public class ValidateCreatedUser implements Task {
    private final CreateUserDataTable data;

    public ValidateCreatedUser(CreateUserDataTable data) {
        this.data = data;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.should(
                seeThatResponse(String.format("The code response is %s", data.getCodeResponse()),
                        response -> response
                                .statusCode(Integer.parseInt(data.getCodeResponse()))
                ));

        if (data.getCodeResponse().equals("201")) {
            actor.should(
                    seeThatResponse("The json response body has the correct format",
                            validateResponse -> validateResponse
                                    .body(matchesJsonSchemaInClasspath("schemas/create_user_json_response_hp.json"))
                    ),
                    seeThatResponse("The json response body has the correct information",
                            response -> response
                                    .body("message", equalTo(data.getDescription()))
                                    .body("type", equalTo(data.getType()))
                    ),
                    seeThat("The info in database", TheUserCreatedInDb.isVerified(this.data), is(Boolean.valueOf(this.data.getDatabaseCheck())))
            );
        } if(data.getCodeResponse().equals("400")) {
            actor.should(
                    seeThatResponse("The json response body has the correct format",
                            response -> response
                                    .body(matchesJsonSchemaInClasspath("schemas/create_user_json_response_fe.json"))
                    ),
                    seeThatResponse("The json response body has the correct information",
                            response -> response
                                    .body("errors.description", equalTo(data.getDescription()))
                                    .body("errors.type", equalTo(data.getType()))
                    ),
                    seeThat("The info in database", TheUserCreatedInDb.isVerified(this.data), is(Boolean.valueOf(this.data.getDatabaseCheck())))
            );
        }
    }


    public static ValidateCreatedUser validateCreatedUser(CreateUserDataTable data) {
        return instrumented(ValidateCreatedUser.class, data);
    }


}
