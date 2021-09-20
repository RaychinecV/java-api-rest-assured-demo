package com.vasil.raichynets.api.service.user.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateUser extends User {

    private @JsonProperty("updatedAt")
    String updatedAt;

}
