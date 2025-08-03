package org.fastcampus.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fastcampus.user.application.dto.CreateUserReqDto;
import org.fastcampus.user.domain.User;
import org.junit.jupiter.api.Test;

public class UserServiceTest {
    private final UserService userService;

    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUser() {
        //given
        CreateUserReqDto reqDto = new CreateUserReqDto("user1", "");

        //when
        User newUser = userService.createUser(reqDto);

        //then
        User findUser = userService.getUser(newUser.getId());
        assertEquals(newUser, findUser);
    }

}
