package com.vasil.raichynets.api.service.user;

import com.vasil.raichynets.api.service.BaseService;
import com.vasil.raichynets.api.service.user.data.User;

import static io.restassured.RestAssured.given;
import static java.util.Objects.requireNonNull;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.module.jsv.JsonSchemaValidator;
import lombok.extern.log4j.Log4j2;

import java.io.File;

/**
 * @author Vasil Raichynets
 */

@Log4j2
public class UserService extends BaseService {

    private final static String GET = "/users/{id}";
    private final static String POST = "/users";
    private final static String PUT = "/users/{id}";
    private final static String PATCH = "/users/{id}";
    private final static String DELETE = "/users/{id}";

    public User getUser(final String id, final int expectedStatusCode) {
        requireNonNull(id, "User id can not be null.");

        log.info("Try get user with ID <" + id + ">");
        final User user =
                given()
                        .spec(getRequestSpec())
                        .pathParam("id", id)
                .when()
                        .get(GET)
                .then()
                        .spec(getResponseSpec(expectedStatusCode))
                        .log().status()
                        .extract()
                        .body()
                        .as(User.class);
        log.info("Have got user with ID <" + id + ">.");

        return user;
    }

    public User createUser(final User newUser, final int expectedStatusCode) {
        requireNonNull(newUser, "User can not be null.");

        log.info("Create new user " + newUser);
        final User createdUser =
            given()
                    .spec(getRequestSpec())
                    .body(newUser)
            .when()
                    .post(POST)
            .then()
                .spec(getResponseSpec(expectedStatusCode))
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createUser.json"))
                .log().status()
                .extract()
                .body()
                .as(User.class);

        log.info("Created user " + createdUser);
        return createdUser;
    }

    public User putUser(final String id, final User newUser, final int expectedStatusCode) {
        requireNonNull(id, "User id can not be null.");
        requireNonNull(newUser, "User can not be null.");

        log.info("Update user by put " + newUser);
        final User updatedUser =
                given()
                        .spec(getRequestSpec())
                        .pathParam("id", id)
                        .body(newUser)
                .when()
                        .put(PUT)
                .then()
                        .spec(getResponseSpec(expectedStatusCode))
                        .log().status()
                        .extract()
                        .as(User.class);
        log.info("User updated " + updatedUser);
        return updatedUser;
    }

    public User patchUser(final String id, final User newUser, final int expectedStatusCode) {
        requireNonNull(id, "User id can not be null.");
        requireNonNull(newUser, "User can not be null.");

        log.info("Update user by patch " + newUser);
        final User updatedUser =
                given()
                        .spec(getRequestSpec())
                        .pathParam("id", id)
                        .body(newUser)
                .when()
                        .patch(PATCH)
                .then()
                        .spec(getResponseSpec(expectedStatusCode))
                        .log().status()
                        .extract()
                        .as(User.class);
        log.info("User updated " + updatedUser);
        return updatedUser;
    }


    public void deleteUser(final String id, final int expectedStatusCode) {
        requireNonNull(id, "User id can not be null.");

        log.info("Delete user with id <" + id + ">");

                given()
                        .spec(getRequestSpec())
                        .pathParam("id", id)
                .when()
                        .delete(DELETE)
                .then()
                        .spec(getResponseSpec(expectedStatusCode))
                        .log().status();
    }
}
