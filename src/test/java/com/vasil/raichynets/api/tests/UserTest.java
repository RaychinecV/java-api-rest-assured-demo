package com.vasil.raichynets.api.tests;

import com.vasil.raichynets.api.service.user.UserService;
import com.vasil.raichynets.api.service.user.data.User;
import com.vasil.raichynets.api.service.user.factory.UserFactory;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

@Log4j2
public class UserTest extends BaseTest {

    UserService userService = new UserService();

    @Test(description = "Get user")
    public void getUser() {
        User user = userService.getUser("1", 200);
        assertThat(user)
                .as("Get user can not be null")
                .isNotNull();
    }

    @Test(description = "Create user")
    public void createUser() {
        User newUser = new UserFactory().create();
        User createdUser = userService.createUser(newUser, 201);
        assertThat(createdUser)
                .as("Create user can not be null")
                .isNotNull();
        assertThat(newUser.equals(createdUser))
                .as("User data should be same")
                .isEqualTo(true);
    }

    @Test(description = "Update user by PUT")
    public void updateUserByPut() {
        User newUser = new UserFactory().create();
        User updateUser = userService.putUser("2", newUser, 200);
        assertThat(updateUser)
                .as("Updated user can not be null")
                .isNotNull();
        assertThat(updateUser.getUpdatedAt())
                .as("Updated data should be same")
                .isNotNull();
        assertThat(updateUser.equals(newUser))
                .as("Users should be same")
                .isEqualTo(true);
    }

    @Test(description = "Update user by PATCH")
    public void updateUserByPatch() {
        User newUser = new UserFactory().create();
        User updateUser = userService.patchUser("2", newUser, 200);
        assertThat(updateUser)
                .as("Updated user can not be null")
                .isNotNull();
        assertThat(updateUser.getUpdatedAt())
                .as("Updated data should be same")
                .isNotNull();
        assertThat(updateUser.equals(newUser))
                .as("Users should be same")
                .isEqualTo(true);
    }

    @Test(description = "Delete user")
    public void deleteUser() {
        userService.deleteUser("2", 204);
    }
}

