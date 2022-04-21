package services;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseService {

    protected static RequestSpecification defaultRequestSpecification(){
        return restAssured()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .header("Authorization", "Bearer 933576acf7187e1b693c0402d9edad5fd23f1ab8ef9a6f5d2827494c1ce7adc7");


    }

    protected static RequestSpecification restAssured() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.urlEncodingEnabled = false;

        return given()
                .config(RestAssuredConfig.config()
                        .encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
    }
}
