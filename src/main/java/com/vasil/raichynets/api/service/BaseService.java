package com.vasil.raichynets.api.service;

import com.vasil.raichynets.api.constant.Endpoints;

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

    protected ResponseSpecification setResponseSpec(final int expectStatusCode) {
        log.info("The response information:");
        return new ResponseSpecBuilder()
                .expectStatusCode(expectStatusCode)
                .log(LogDetail.STATUS)
                .log(LogDetail.BODY)
                .build();
    }

    protected RequestSpecification setRequestSpec(final ContentType contentType) {
        log.info("The request information:");
        return new RequestSpecBuilder()
                .setBaseUri(Endpoints.BASE_URI)
                .setAccept(contentType)
                .setContentType(contentType)
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .log(LogDetail.BODY)
                .build();
    }
}
