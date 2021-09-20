package com.vasil.raichynets.api.constant.scheme_paths;

import lombok.Getter;

@Getter
public enum UserSchemePaths {

    GET_USER("userSchemes/getUserScheme.json"),
    USERS_PAGE("userSchemes/usersPageScheme.json"),
    CREATE_USER("userSchemes/createUserScheme.json"),
    UPDATE_USER("userSchemes/updateUserScheme.json");

    private String path;

    UserSchemePaths(String path) {
        this.path = path;
    }

}
