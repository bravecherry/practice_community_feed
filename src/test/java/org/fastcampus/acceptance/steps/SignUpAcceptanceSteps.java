package org.fastcampus.acceptance.steps;

import io.restassured.RestAssured;
import org.fastcampus.auth.application.dto.SendEmailReqDto;
import org.springframework.http.MediaType;

public class SignUpAcceptanceSteps {

    public static Integer requestSendEmail(SendEmailReqDto reqDto) {
        return RestAssured
                .given()
                .body(reqDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/sign/up/send-verification/email")
                .then()
                .extract()
                .jsonPath().get("code");
    }

}
