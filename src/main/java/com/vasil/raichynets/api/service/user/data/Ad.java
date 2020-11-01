package com.vasil.raichynets.api.service.user.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Vasil Raichynets
 */

@Data
public class Ad {
    private @JsonProperty("company")
    String company;
    private @JsonProperty("text")
    String text;
    private @JsonProperty("url")
    String url;
}
