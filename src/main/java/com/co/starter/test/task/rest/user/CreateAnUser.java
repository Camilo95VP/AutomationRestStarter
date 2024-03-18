package com.co.starter.test.task.rest.user;

import com.co.starter.test.models.json.request.generator.user.create.UserCommandBodyGenerator;
import com.co.starter.test.models.json.request.model.user.create.UserCommand;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.RestQueryFunction;

import com.co.starter.test.models.datatables.user.CreateUserDataTable;
import net.thucydides.model.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateAnUser implements Task {
    private final CreateUserDataTable data;
    @Managed
    private EnvironmentVariables environmentVariables;
    RestQueryFunction restConfiguration;

    public CreateAnUser(CreateUserDataTable data) {
        this.data = data;
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        UserCommandBodyGenerator userCommandBodyGenerator = new UserCommandBodyGenerator(this.data, actor);
        UserCommand body = userCommandBodyGenerator.generate();
        String endpoint = this.environmentVariables.optionalProperty("endpoints.user.create").orElse("/");

        restConfiguration = requestSpecification -> {
            requestSpecification.body(body);
            return requestSpecification;
        };

        actor.attemptsTo(
                Post.to(endpoint).with(restConfiguration)
        );

        actor.remember("firstName", body.getFirstName());
    }

    public static CreateAnUser createAnUser(CreateUserDataTable data) {
        return instrumented(CreateAnUser.class, data);
    }
}
