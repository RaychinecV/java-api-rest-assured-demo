package com.vasil.raichynets.api.service.user.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true, fluent = true)
public class UsersPage {
    private @JsonProperty("page")
    int page;
    private @JsonProperty("per_page")
    int perPage;
    private @JsonProperty("total")
    int total;
    private @JsonProperty("total_pages")
    int totalPages;
    private @JsonProperty("data")
    List<Data> data;
    private @JsonProperty("support")
    Support support;
}
