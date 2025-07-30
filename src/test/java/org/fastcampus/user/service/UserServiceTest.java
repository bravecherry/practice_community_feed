package org.fastcampus.user.service;

import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.fastcampus.user.domain.UserProfileReadDto;

public class UserServiceTest {
    private final UserService userService;

    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    void getUserProfile() {
        //given
        User user = new User(1L, new UserInfo("swoo", "profileURL"));
        //when
        UserProfileReadDto dto = userService.getUserProfile(user.getId());
        //then
        assert dto.getId().equals(user.getId());
    }
}
