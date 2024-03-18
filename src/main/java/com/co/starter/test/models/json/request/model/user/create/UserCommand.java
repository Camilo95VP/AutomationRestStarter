package com.co.starter.test.models.json.request.model.user.create;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCommand {
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String address;
    private String city;
}
