package com.co.starter.test.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/create_user.feature",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {
                "com.co.starter.test.stepdefinitions",
        },
        tags = "@EN0001-HP or @EN0001-FE",
        plugin = {"pretty"}
)
public class CreateUserRunner {
}
