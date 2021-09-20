package com.vasil.raichynets.api.service.user.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUser extends User {
    private @JsonProperty("id")
    int id;
    private @JsonProperty("createdAt")
    String createdAt;
}
