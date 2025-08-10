package org.fastcampus.post.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostVisibleState;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

public class PostTest {

    private final UserInfo userInfo = new UserInfo("user1", "");
    private final User author = new User(1L, userInfo);
    private final User user = new User(2L, new UserInfo("user2", ""));
    private final PostContent content = new PostContent("post content must be long");
    private final PostVisibleState state = PostVisibleState.PUBLIC;
    private final Post post = new Post(null, author, content, state);

    @Test
    void givenCreated_WhenAuthorIsNotNull_ThenCreated() {
        //when
        Post post = new Post(null, author, content, state);
        //then
        assertEquals(author, post.getAuthor());
        assertEquals(content.getContent(), post.getContentMessage());
        assertEquals(state, post.getVisibleState());
    }

    @Test
    void givenCreated_WhenAuthorIsNull_ThenThrowException() {
        //given
        User emptyAuthor = null;
        //then
        assertThrows(IllegalArgumentException.class, () -> new Post(null, emptyAuthor, content, state));
    }

    @Test
    void givenCreated_WhenLikedByUser_ThenLikeCounterIncrease() {
        //when
        post.getLike(user);
        //then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenCreated_WhenLikedByAuthor_ThenThrowsException() {
        //then
        assertThrows(IllegalArgumentException.class, () -> post.getLike(author));
    }

    @Test
    void givenLikedPost_WhenLoseLikeByUser_ThenLikeCounterDecrease() {
        //when
        post.getLike(user);

        //when
        post.loseLike();

        //then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenCreated_WhenLoseLikeByUser_ThenLikeCounterDecrease() {
        //when
        post.loseLike();

        //then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenContent_whenUpdateContent_ThenUpdated() {
        //given
        String updateContent = "post content must be long222";
        PostVisibleState updateState = PostVisibleState.FOLLOWER_ONLY;
        //when
        post.updatePost(author, updateContent, updateState);
        //then
        assertEquals(updateContent, post.getContentMessage());
        assertEquals(updateState, post.getVisibleState());
    }

    @Test
    void givenContent_whenUserIsNotAuthor_ThenThrowsException() {
        //given
        String updateContent = "post content must be long222";
        PostVisibleState updateState = PostVisibleState.FOLLOWER_ONLY;
        //then
        assertThrows(IllegalArgumentException.class, () -> post.updatePost(user, updateContent, updateState));
    }

    @Test
    void givenContent_whenUserIsAuthor_ThenTrue() {
        //given
        //then
        assertTrue(post.isAuthor(author));
    }

    @Test
    void givenContent_whenUserIsNotAuthor_ThenFalse() {
        //then
        assertFalse(post.isAuthor(user));
    }

}
