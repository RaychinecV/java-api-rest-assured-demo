package com.vasil.raichynets.api.service.user.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    protected String name;
    protected String job;

    public User(final Consumer<User> builder) {
        requireNonNull(builder).accept(this);
    }
}


