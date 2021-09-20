package com.vasil.raichynets.api.service.user;

import com.vasil.raichynets.api.service.BaseService;
import com.vasil.raichynets.api.service.user.data.*;

import static com.vasil.raichynets.api.constant.endpoints.Endpoints.*;
import static com.vasil.raichynets.api.constant.scheme_paths.UserSchemePaths.*;
import static io.restassured.RestAssured.given;
import static java.util.Objects.requireNonNull;

import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class UserService extends BaseService {

    @Step
    public GetUser getUser(final int id) {
        return this.getUser(id, 200, true);
    }

    @Step
    public GetUser getUser(final int id, final int expectedStatusCode) {
        return this.getUser(id, expectedStatusCode, true);
    }

    @Step
    public GetUser getUser(final int id, final int expectedStatusCode, final boolean isExist) {

        log.info("Getting user with ID <{}>.", id);
        final GetUser user =
                given()
                        .pathParam("id", id)

                        .when()
                        .get(USER_RESOURCE + USER_PATH_PARAMETER_ID)

                        .then()
                        .log().body()
                        .log().status()
                        .statusCode(expectedStatusCode)
                        .body(isExist ? JsonSchemaValidator.matchesJsonSchemaInClasspath(GET_USER.getPath()) : Matchers.is("{}"))
                        .extract()
                        .body()
                        .as(GetUser.class);
        log.info("User with id {} is found {}.", id, user);

        return user;
    }

    @Step
    public UsersPage getUsersPage() {
        return this.getUsersPage(0, 200);
    }

    @Step
    public UsersPage getUsersPage(final int pageNumber) {
        return this.getUsersPage(pageNumber, 200);
    }

    @Step
    public boolean isUserPresentInUsersPages(final GetUser user) {

        return this.getUsersPages().stream()
                .anyMatch(page -> page.data()
                        .stream().anyMatch(actualUser -> actualUser.equals(user.data())));
    }

    @Step
    public List<UsersPage> getUsersPages() {
        List<UsersPage> usersPages = new ArrayList<>();

        int totalPages = this.getUsersPage().totalPages();
        while (totalPages > 0) {
            UsersPage usersPage = this.getUsersPage(totalPages);
            usersPages.add(usersPage);
            totalPages--;
        }
        return usersPages;
    }

    @Step
    public UsersPage getUsersPage(final int pageNumber, final int expectedStatusCode) {

        log.info("Getting users.");
        final UsersPage usersPage =
                given()
                        .queryParam(USERS_QUERY_PARAMETER_PAGE, pageNumber)

                        .when()
                        .get(USER_RESOURCE)

                        .then()
                        .statusCode(expectedStatusCode)
                        .log().body()
                        .log().status()
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(USERS_PAGE.getPath()))
                        .extract()
                        .body()
                        .as(UsersPage.class);
        log.info("Users is found {}.", usersPage);

        return usersPage;
    }

    @Step
    public CreateUser createUser(final User newUser) {
        return this.createUser(newUser, 200);
    }

    @Step
    public CreateUser createUser(final User newUser, final int expectedStatusCode) {
        requireNonNull(newUser, "User can not be null.");

        log.info("Creating new user {}.", newUser);
        final CreateUser createdUser =
                given()
                        .body(newUser)

                        .when()
                        .post(USER_RESOURCE)

                        .then()
                        .assertThat()
                        .statusCode(expectedStatusCode)
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(CREATE_USER.getPath()))
                        .extract()
                        .body()
                        .as(CreateUser.class);
        log.info("User is created {}.", createdUser);

        return createdUser;
    }

    @Step
    public UpdateUser putUser(final int id, final User user, final int expectedStatusCode) {
        requireNonNull(user, "User can not be null.");

        log.info("Update user by put " + user);
        final UpdateUser updatedUser =
                given()
                        .pathParam("id", id)
                        .body(user)

                        .when()
                        .put(USER_RESOURCE + USER_PATH_PARAMETER_ID)

                        .then()
                        .assertThat()
                        .statusCode(expectedStatusCode)
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(UPDATE_USER.getPath()))
                        .extract()
                        .body()
                        .as(UpdateUser.class);
        log.info("User is updated by put {}.", updatedUser);

        return updatedUser;
    }

    @Step
    public UpdateUser patchUser(final int id, final User user, final int expectedStatusCode) {
        requireNonNull(user, "User can not be null.");

        log.info("Update user by patch " + user);
        final UpdateUser updatedUser =
                given()
                        .pathParam("id", id)
                        .body(user)

                        .when()
                        .patch(USER_RESOURCE + USER_PATH_PARAMETER_ID)

                        .then()
                        .assertThat()
                        .statusCode(expectedStatusCode)
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(UPDATE_USER.getPath()))
                        .extract()
                        .body()
                        .as(UpdateUser.class);
        log.info("User is updated by patch {}.", updatedUser);

        return updatedUser;
    }

    @Step
    public void deleteUser(final int id, final int expectedStatusCode) {

        log.info("Deleting user with id <{}>.", id);
        given()
                .pathParam("id", id)

                .when()
                .delete(USER_RESOURCE + USER_PATH_PARAMETER_ID)

                .then()
                .assertThat()
                .statusCode(expectedStatusCode)
                .body(Matchers.is(""));
    }
}
