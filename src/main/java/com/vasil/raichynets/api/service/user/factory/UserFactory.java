package com.vasil.raichynets.api.service.user.factory;

import java.util.Locale;

import com.vasil.raichynets.api.service.Factory;
import com.vasil.raichynets.api.service.user.data.CreateUser;
import com.vasil.raichynets.api.service.user.data.User;

import com.devskiller.jfairy.Fairy;

import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Vasil Raichynets
 */

@Setter
@Accessors(chain = true)
public class UserFactory implements Factory<User, UserFactory> {
    private static final Fairy FAIRY = Fairy.create(Locale.US);
    private String name;
    private String job;

    @Override
    public User create() {
        final String userName = name != null ? name : FAIRY.person().getFirstName();
        final String userJob = job != null ? job : FAIRY.person().getCompany().getName();
        return new User(user -> {
            user.setName(userName);
            user.setJob(userJob);
        });
    }
}
