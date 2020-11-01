package com.vasil.raichynets.api.service.user.data;

import java.util.Objects;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Vasil Raichynets
 */

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private @JsonProperty("ad")
    Ad ad;
    private @JsonProperty("data")
    Data data;
    private @JsonProperty("createdAt")
    String createdAt;
    private @JsonProperty("name")
    String name;
    private @JsonProperty("id")
    String id;
    private @JsonProperty("job")
    String job;
    private @JsonProperty("updatedAt")
    String updatedAt;

    public User() {
        super();
    }

    @Override
    public boolean equals(final Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final User user = (User) object;
        return Objects.equals(name, user.name)
                && Objects.equals(job, user.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, job);
    }

    public User(final Consumer<User> builder) {
        requireNonNull(builder).accept(this);
    }
}
