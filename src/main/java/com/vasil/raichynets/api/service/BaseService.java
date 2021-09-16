package com.vasil.raichynets.api.service;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import lombok.extern.log4j.Log4j2;

/**
 * @author Vasil Raichynets
 */

@Log4j2
public class BaseService {

    public static final String BASE_URI = "https://reqres.in/api";

/*    static {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
    }*/

    protected ResponseSpecification getResponseSpec(final int expectStatusCode) {
        log.info("The response information:");
        return new ResponseSpecBuilder()
                .expectStatusCode(expectStatusCode)
                .log(LogDetail.STATUS)
                .log(LogDetail.BODY)
                .build();
    }

    protected RequestSpecification getRequestSpec() {
        log.info("The request information:");
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.BODY)
                .build();
    }
}
