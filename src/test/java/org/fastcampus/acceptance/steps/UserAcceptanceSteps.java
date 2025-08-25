package org.fastcampus.acceptance.steps;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.fastcampus.user.application.dto.CreateUserReqDto;
import org.fastcampus.user.application.dto.FollowUserReqDto;
import org.springframework.http.MediaType;

public class UserAcceptanceSteps {

    public static ExtractableResponse<Response> createUser(CreateUserReqDto reqDto) {
        return RestAssured
                .given()
                // 리퀘스트 바디 유형으로 CreateUserReqDto.class가 들어왔을때
                .body(reqDto)
                // content type: application/json
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                // post 요청 수행
                .post("/user")
                .then()
                .extract();
    }

    public static ExtractableResponse<Response> followUser(FollowUserReqDto reqDto) {
        return RestAssured
                .given()
                .body(reqDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/relation/follow")
                .then()
                .extract();
    }

}
