package org.fastcampus.acceptance.steps;

import io.restassured.RestAssured;
import java.util.List;
import org.fastcampus.post.application.dto.CreatePostReqDto;
import org.fastcampus.post.ui.dto.GetPostContentResDto;
import org.springframework.http.MediaType;

public class FeedAcceptanceStep {

    public static Long reqCreatePost(CreatePostReqDto reqDto) {
        return RestAssured
                .given().log().all()
                .body(reqDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/relation/follow")
                .then().log().all()
                .extract()
                .jsonPath()
                .getObject("value", Long.class);
    }

    public static List<GetPostContentResDto> requestFeed(Long userId) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/feed/{userId}")
                .then().log().all()
                .extract()
                .jsonPath()
                .getList("value", GetPostContentResDto.class);
    }

}
