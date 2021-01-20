package com.vasil.raichynets.api.service.user.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Vasil Raichynets
 */

@lombok.Data
public class Support {
    private @JsonProperty("url")
    String company;
    private @JsonProperty("text")
    String text;
}
