package com.vasil.raichynets.api.service.user;

import com.vasil.raichynets.api.constant.Endpoints;
import com.vasil.raichynets.api.service.BaseService;
import com.vasil.raichynets.api.service.user.data.User;

import static io.restassured.RestAssured.given;
import static java.util.Objects.requireNonNull;

import io.restassured.http.ContentType;

import lombok.extern.log4j.Log4j2;

/**
 * @author Vasil Raichynets
 */

@Log4j2
public class UserService extends BaseService {

    public User getUser(final String id, final int expectedStatusCode) {
        requireNonNull(id, "User id can not be null.");

        log.info("Try get user with ID <" + id + ">");
        final User user =
                given()
                        .spec(setRequestSpec(ContentType.JSON))
                        .pathParam("id", id)
                .get(Endpoints.GET_USER)
                .then()
                        .spec(setResponseSpec(expectedStatusCode))
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
                .spec(setRequestSpec(ContentType.JSON))
                .body(newUser)
            .post(Endpoints.CREATE_USER)
            .then()
                .spec(setResponseSpec(expectedStatusCode))
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
                    .spec(setRequestSpec(ContentType.JSON))
                    .pathParam("id", id)
                    .body(newUser)
                .put(Endpoints.GET_USER)
                .then()
                    .spec(setResponseSpec(expectedStatusCode))
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
                    .spec(setRequestSpec(ContentType.JSON))
                    .pathParam("id", id)
                    .body(newUser)
                .patch(Endpoints.GET_USER)
                .then()
                    .spec(setResponseSpec(expectedStatusCode))
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
                    .spec(setRequestSpec(ContentType.JSON))
                    .pathParam("id", id)
                .delete(Endpoints.GET_USER)
                .then()
                    .spec(setResponseSpec(expectedStatusCode))
                    .log().status();
    }
}
