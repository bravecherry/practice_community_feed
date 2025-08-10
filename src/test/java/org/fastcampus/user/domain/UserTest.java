package org.fastcampus.user.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private final UserInfo userInfo1 = new UserInfo("user1", "");
    private final UserInfo userInfo2 = new UserInfo("user2", "");
    private User user1;
    private User user2;

    @BeforeEach
    void init() {
        user1 = new User(1L, userInfo1);
        user2 = new User(2L, userInfo2);
    }

    @Test
    void givenTwoUsers_whenEqual_thenReturnFalse() {
        assertNotEquals(user1, user2);
    }

    @Test
    void givenTwoSameIdUsers_whenEqual_thenReturnTrue() {
        //given
        User sameUser = new User(1L, userInfo1);
        //then
        assertEquals(user1, sameUser);
    }

    @Test
    void givenTwoUsers_whenHashCode_thenReturnFalse() {
        //given
        int hashCode1 = user1.hashCode();
        int hashCode2 = user2.hashCode();
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    void givenTwoSameIdUsers_whenHashcode_thenReturnTrue() {
        //given
        User sameUser = new User(1L, userInfo1);
        int hashCode1 = user1.hashCode();
        int hashCode2 = sameUser.hashCode();
        //then
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void givenTwoUsers_whenUser1FollowsUser2_thenUserCountIncrease() {
        //when
        user1.follows(user2);
        //then
        assertEquals(1, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(1, user2.getFollowerCount());
    }

    @Test
    void givenUser1FollowsUser2_whenUser1UnfollowsUser2_thenUserCountDecrease() {
        //given
        user1.follows(user2);

        //when
        user1.unfollows(user2);

        //then
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
    }

    @Test
    void givenNoneFollowers_whenUser1UnfollowsUser2_thenUserCountNotDecrease() {
        //when
        user1.unfollows(user2);

        //then
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user1.getFollowerCount());
        assertEquals(0, user2.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
    }

}
