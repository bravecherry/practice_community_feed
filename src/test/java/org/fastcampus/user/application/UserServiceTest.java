package org.fastcampus.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fastcampus.user.application.dto.CreateUserReqDto;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Test;

public class UserServiceTest {
    private final FakeUserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);

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
