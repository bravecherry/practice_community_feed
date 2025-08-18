package org.fastcampus.acceptance.utils;

import static org.fastcampus.acceptance.steps.UserAcceptanceStep.createUser;
import static org.fastcampus.acceptance.steps.UserAcceptanceStep.followUser;

import org.fastcampus.user.application.dto.CreateUserReqDto;
import org.fastcampus.user.application.dto.FollowUserReqDto;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    public void loadData() {
        CreateUserReqDto reqDto = new CreateUserReqDto("testUser1", "");

        // id가 전부 다를 것이다.
        createUser(reqDto);
        createUser(reqDto);
        createUser(reqDto);

        followUser(new FollowUserReqDto(1L, 2L));
        followUser(new FollowUserReqDto(1L, 3L));
    }
}
