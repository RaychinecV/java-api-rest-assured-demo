package com.vasil.raichynets.api.service.user.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Vasil Raichynets
 */

@lombok.Data
public class Data {
    private @JsonProperty("id")
    int id;
    private @JsonProperty("email")
    String email;
    private @JsonProperty("first_name")
    String firstName;
    private @JsonProperty("last_name")
    String lastName;
    private @JsonProperty("avatar")
    String avatar;

}
