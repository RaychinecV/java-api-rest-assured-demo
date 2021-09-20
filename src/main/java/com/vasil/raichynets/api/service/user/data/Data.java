package com.vasil.raichynets.api.service.user.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true, fluent = true)
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
