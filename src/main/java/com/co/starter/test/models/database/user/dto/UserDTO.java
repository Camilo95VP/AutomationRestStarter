package com.co.starter.test.models.database.user.dto;


import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @SerializedName("id")
    private int userId;
    @SerializedName("address")
    private String address;
    @SerializedName("city")
    private String city;
    @SerializedName("email")
    private String email;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("telephone")
    private String telephone;

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
