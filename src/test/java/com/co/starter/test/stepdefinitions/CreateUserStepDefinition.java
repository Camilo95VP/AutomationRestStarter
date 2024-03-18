package com.co.starter.test.stepdefinitions;

import com.co.starter.test.models.datatables.user.CreateUserDataTable;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.util.EnvironmentVariables;


import java.util.Map;

import static com.co.starter.test.task.rest.user.CreateAnUser.createAnUser;
import static com.co.starter.test.task.rest.user.ValidateCreatedUser.validateCreatedUser;


public class CreateUserStepDefinition {
    @Managed
    private Actor actor;
    @Managed
    private EnvironmentVariables environmentVariables;

    @DataTableType
    public CreateUserDataTable createUserDataTable(Map<String, String> table) {
        return new ObjectMapper().convertValue(table, CreateUserDataTable.class);
    }

    @Before
    public void setTheStage() {
        String theRestApiBaseUrl = this.environmentVariables.optionalProperty("restapi.baseurl.url")
                .orElse("http://");

        OnStage.setTheStage(new OnlineCast());
        actor = Actor.named("User").whoCan(CallAnApi.at(theRestApiBaseUrl));
    }

    @When("the backend server calls the microservice to register an user with the data")
    public void theUserCallTheServiceWithTheData(CreateUserDataTable data) {
        actor.attemptsTo(
                createAnUser(data)
        );
        String firstNameRecord = actor.recall("firstName");
        actor.remember("oldFirstName", firstNameRecord);
    }

    @Then("the response data is validated")
    public void theResponseDataIsValidated(CreateUserDataTable data) {
        actor.attemptsTo(
                validateCreatedUser(data)
        );
    }

}
