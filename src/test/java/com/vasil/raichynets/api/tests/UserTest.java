package com.vasil.raichynets.api.tests;

import com.vasil.raichynets.api.service.user.UserService;
import com.vasil.raichynets.api.service.user.data.*;
import com.vasil.raichynets.api.service.user.factory.UserFactory;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

@Log4j2
public class UserTest extends BaseTest {

    UserService userService = new UserService();

    @Test(description = "Get an user")
    public void getUser() {
        GetUser user = userService.getUser(2, 200);

        assertThat(user)
                .as("Get user can not be null")
                .isNotNull();
    }

    @Test(description = "Search user in the users pages")
    public void searchUserInTheUsersPages() {

        final GetUser user = userService.getUser(12);
        final boolean userFound = userService.isUserPresentInUsersPages(user);

        assertThat(userFound).as("User should be present in the user pages").isTrue();
    }

    @Test(description = "Get users")
    public void getUsers() {
        final int pageNumber = 2;
        final UsersPage usersPage = userService.getUsersPage(pageNumber);

        assertThat(usersPage).as("Get user can not be null").isNotNull();
        assertThat(usersPage.data().size() <= usersPage.perPage()).as("Users size and per_size field should be same or less!!!");
        assertThat(usersPage.page()).as("Expected page and actual page should be same.").isEqualTo(pageNumber);

    }

    @Test(description = "Get not exist user")
    public void getUserNotFound() {
        GetUser user = userService.getUser(23, 404, false);

        assertThat(user.data())
                .as("User data for not exist user should be null")
                .isNull();
        assertThat(user.support())
                .as("User support for not exist user should be null")
                .isNull();
    }

    @Test(description = "Create an user")
    public void createUser() {
        User newUser = new UserFactory().create();
        CreateUser createdUser = userService.createUser(newUser, 201);

        assertThat(createdUser)
                .as("Create user can not be null")
                .isNotNull();
        assertThat(createdUser.getCreatedAt())
                .as("Created data should be not null")
                .isNotNull();
        assertThat(newUser.equals(createdUser))
                .as("User data should be same")
                .isEqualTo(true);
    }

    @Test(description = "Update an user by PUT")
    public void updateUserByPut() {
        User newUser = new UserFactory().create();
        UpdateUser updatedUser = userService.putUser(2, newUser, 200);

        assertThat(updatedUser)
                .as("Updated user can not be null")
                .isNotNull();
        assertThat(updatedUser.getUpdatedAt())
                .as("Updated data should be not null")
                .isNotNull();
        assertThat(newUser.equals(updatedUser))
                .as("Users should be same")
                .isEqualTo(true);
    }

    @Test(description = "Update an user by PATCH")
    public void updateUserByPatch() {
        User newUser = new UserFactory().create();
        UpdateUser updatedUser = userService.patchUser(2, newUser, 200);

        assertThat(updatedUser)
                .as("Updated user can not be null")
                .isNotNull();
        assertThat(updatedUser.getUpdatedAt())
                .as("Updated data should be not null")
                .isNotNull();
        assertThat(newUser.equals(updatedUser))
                .as("Users should be same")
                .isEqualTo(true);
    }

    @Test(description = "Delete an user")
    public void deleteUser() {
        userService.deleteUser(2, 204);
    }
}

