package org.fastcampus.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.user.application.dto.CreateUserReqDto;
import org.fastcampus.user.application.dto.FollowUserReqDto;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.FakeUserRelationRepository;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRelationServiceTest {
    private final FakeUserRepository userRepository = new FakeUserRepository();
    private final FakeUserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private final UserService userService = new UserService(userRepository);
    private final UserRelationService userRelationService = new UserRelationService(userRelationRepository, this.userService);
    private User user;
    private User targetUser;
    private FollowUserReqDto reqDto;

    @BeforeEach
    void setUp() {
        this.user = userService.createUser(new CreateUserReqDto("user1", ""));
        this.targetUser = userService.createUser(new CreateUserReqDto("user2", ""));

        this.reqDto = new FollowUserReqDto(user.getId(), targetUser.getId());
    }

    @Test
    void givenTwoUsers_whenUserFollowsTargetUser_thenFollowSaved() {
        //when
        userRelationService.follow(this.reqDto);

        //then
        assertEquals(1, user.getFollowingCount());
        assertEquals(0, user.getFollowerCount());
        assertEquals(1, targetUser.getFollowerCount());
        assertEquals(0, targetUser.getFollowingCount());
    }

    @Test
    void givenTwoUsers_whenUserFollowsTargetUserWhomUserAlreadyFollows_thenErrorThrows() {
        //given
        userRelationService.follow(this.reqDto);

        //then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(this.reqDto));
    }

    @Test
    void givenOneUser_whenUserFollowsUser_thenErrorThrows() {
        //given
        FollowUserReqDto reqDto = new FollowUserReqDto(user.getId(), user.getId());

        //then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(reqDto));
    }

    @Test
    void givenTwoUsers_whenUserUnfollowsTargetUser_thenUnfollowSaved() {
        //given
        userRelationService.follow(this.reqDto);

        //when
        userRelationService.unfollow(this.reqDto);

        //then
        assertEquals(0, user.getFollowingCount());
        assertEquals(0, user.getFollowerCount());
        assertEquals(0, targetUser.getFollowerCount());
        assertEquals(0, targetUser.getFollowingCount());
    }

    @Test
    void givenTwoUsers_whenUserUnfollowsTargetUserWhomUserNotFollows_thenErrorThrows() {
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(this.reqDto));
    }

    @Test
    void givenOneUser_whenUserUnfollowsUser_thenErrorThrows() {
        //given
        FollowUserReqDto reqDto = new FollowUserReqDto(user.getId(), user.getId());

        //then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(reqDto));
    }

}
