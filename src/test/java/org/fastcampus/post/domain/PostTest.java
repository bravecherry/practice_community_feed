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

    @Test
    void givenCreated_WhenAuthorIsNotNull_ThenCreated() {
        //given
        User author = new User(1L, new UserInfo("user1", ""));
        PostContent content = new PostContent("post content must be long");
        PostVisibleState state = PostVisibleState.PUBLIC;
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
        User author = null;
        PostContent content = new PostContent("post content must be long");
        PostVisibleState state = PostVisibleState.PUBLIC;
        //then
        assertThrows(IllegalArgumentException.class, () -> new Post(null, author, content, state));
    }

    @Test
    void givenCreated_WhenLikedByUser_ThenLikeCounterIncrease() {
        //given
        User author = new User(1L, new UserInfo("user1", ""));
        User user = new User(2L, new UserInfo("user2", ""));
        PostContent content = new PostContent("post content must be long");
        PostVisibleState state = PostVisibleState.PUBLIC;
        Post post = new Post(null, author, content, state);
        //when
        post.getLike(user);
        //then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenCreated_WhenLikedByAuthor_ThenThrowsException() {
        //given
        User author = new User(1L, new UserInfo("user1", ""));
        PostContent content = new PostContent("post content must be long");
        PostVisibleState state = PostVisibleState.PUBLIC;
        Post post = new Post(null, author, content, state);
        //then
        assertThrows(IllegalArgumentException.class, () -> post.getLike(author));
    }

    @Test
    void givenCreated_WhenLoseLikeByUser_ThenLikeCounterDecrease() {
        //given
        User author = new User(1L, new UserInfo("user1", ""));
        User user = new User(2L, new UserInfo("user2", ""));
        PostContent content = new PostContent("post content must be long");
        PostVisibleState state = PostVisibleState.PUBLIC;
        Post post = new Post(null, author, content, state);
        //when
        post.getLike(user);
        post.loseLike();
        //then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenContent_whenUpdateContent_ThenUpdated() {
        //given
        String updateContent = "post content must be long222";
        PostVisibleState updateState = PostVisibleState.FOLLOWER_ONLY;
        User author = new User(1L, new UserInfo("user1", ""));
        PostContent postContent = new PostContent("post content must be long");
        PostVisibleState state = PostVisibleState.PUBLIC;
        Post post = new Post(null, author, postContent, state);
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
        User user = new User(2L, new UserInfo("user2", ""));
        User author = new User(1L, new UserInfo("user1", ""));
        PostContent postContent = new PostContent("post content must be long");
        PostVisibleState state = PostVisibleState.PUBLIC;
        Post post = new Post(null, author, postContent, state);
        //then
        assertThrows(IllegalArgumentException.class, () -> post.updatePost(user, updateContent, updateState));
    }

    @Test
    void givenContent_whenUserIsAuthor_ThenTrue() {
        //given
        User author = new User(1L, new UserInfo("user1", ""));
        PostContent postContent = new PostContent("post content must be long");
        PostVisibleState state = PostVisibleState.PUBLIC;
        Post post = new Post(null, author, postContent, state);
        //then
        assertTrue(post.isAuthor(author));
    }

    @Test
    void givenContent_whenUserIsNotAuthor_ThenFalse() {
        //given
        User author = new User(1L, new UserInfo("user1", ""));
        User user = new User(2L, new UserInfo("user2", ""));
        PostContent postContent = new PostContent("post content must be long");
        PostVisibleState state = PostVisibleState.PUBLIC;
        Post post = new Post(null, author, postContent, state);
        //then
        assertFalse(post.isAuthor(user));
    }

}
