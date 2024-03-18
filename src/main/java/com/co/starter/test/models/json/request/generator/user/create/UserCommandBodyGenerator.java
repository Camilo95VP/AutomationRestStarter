package com.co.starter.test.models.json.request.generator.user.create;

import com.co.starter.test.models.datatables.user.CreateUserDataTable;
import com.co.starter.test.models.json.request.model.user.create.UserCommand;
import com.github.javafaker.Faker;
import net.serenitybdd.screenplay.Actor;

public class UserCommandBodyGenerator {
    private final CreateUserDataTable data;
    private final UserCommand userInfo;
    private final Actor actor;
    private static final String EMPTY_FLAG = "empty";
    Faker faker = new Faker();

    public UserCommandBodyGenerator(CreateUserDataTable data, Actor actor) {
        this.data = data;
        this.actor = actor;
        this.userInfo = UserCommand.builder().build();
    }

    private void setFirstName() {
        String firstName = this.data.getFirstName();
        switch (firstName) {
            case "null" -> this.userInfo.setFirstName(null);
            case EMPTY_FLAG -> this.userInfo.setFirstName("");
            case "OK" -> this.userInfo.setFirstName(faker.name().firstName());
            case "SAME" -> this.userInfo.setFirstName(actor.recall("firstName"));
            default -> this.userInfo.setFirstName(firstName);
        }
    }

    private void setLastName(){
        String lastName = this.data.getLastName();
        switch (lastName) {
            case "null" -> this.userInfo.setLastName(null);
            case EMPTY_FLAG -> this.userInfo.setLastName("");
            case "OK" -> this.userInfo.setLastName(faker.name().lastName());
            default -> this.userInfo.setLastName(lastName);
        }
    }

    private void setEmail() {
        String email = this.data.getEmail();
        switch (email) {
            case "null" -> this.userInfo.setEmail(null);
            case EMPTY_FLAG -> this.userInfo.setEmail("");
            case "OK" -> this.userInfo.setEmail(faker.internet().emailAddress());
            default -> this.userInfo.setEmail(email);
        }
    }

    private void setTelephone() {
        String telephone = this.data.getTelephone();
        switch (telephone) {
            case "null" -> this.userInfo.setTelephone(null);
            case EMPTY_FLAG -> this.userInfo.setTelephone("");
            case "OK" -> this.userInfo.setTelephone(faker.phoneNumber().cellPhone().replaceAll("[^\\d]", ""));
            default -> this.userInfo.setTelephone(telephone);
        }
    }

    private void setAddress() {
        String address = this.data.getAddress();
        switch (address) {
            case "null" -> this.userInfo.setAddress(null);
            case EMPTY_FLAG -> this.userInfo.setAddress("");
            case "OK" -> this.userInfo.setAddress(faker.address().fullAddress());
            default -> this.userInfo.setAddress(address);
        }
    }

    private void setCity() {
        String city = this.data.getCity();
        switch (city) {
            case "null" -> this.userInfo.setCity(null);
            case EMPTY_FLAG -> this.userInfo.setCity("");
            case "OK" -> this.userInfo.setCity(faker.country().capital());
            default -> this.userInfo.setCity(city);
        }
    }

    public UserCommand generate(){
        if(userInfo != null){
            setFirstName();
            setLastName();
            setEmail();
            setTelephone();
            setAddress();
            setTelephone();
            setCity();
        }
        return this.userInfo;
    }
}
