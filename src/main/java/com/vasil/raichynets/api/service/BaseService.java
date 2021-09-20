package com.vasil.raichynets.api.service;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

import lombok.extern.log4j.Log4j2;

import static com.vasil.raichynets.api.constant.endpoints.Endpoints.BASE_URI;

@Log4j2
public abstract class BaseService {

    static {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.HEADERS).log(LogDetail.BODY)
                .build();

        RestAssured.filters(new AllureRestAssured());
    }
}
