package org.fastcampus.acceptance.steps;

import io.restassured.RestAssured;
import org.fastcampus.auth.application.dto.CreateUserAuthReqDto;
import org.fastcampus.auth.application.dto.SendEmailReqDto;
import org.springframework.http.MediaType;

public class SignUpAcceptanceSteps {

    public static Integer requestSendEmail(SendEmailReqDto reqDto) {
        return RestAssured
                .given()
                .body(reqDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/sign/up/verification/send/email")
                .then()
                .extract()
                .jsonPath().get("code");
    }

    public static Integer requestVerifyEmail(String email, String token) {
        return RestAssured
                .given()
                .queryParam("email", email)
                .queryParam("token", token)
                .when()
                .get("/sign/up/verify/token")
                .then()
                .extract()
                .jsonPath().get("code");
    }

    public static Integer registerUser(CreateUserAuthReqDto reqDto) {
        return RestAssured
                .given()
                .body(reqDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/sign/up/register")
                .then()
                .extract()
                .jsonPath().get("code");
    }

}
