package org.fastcampus.post.domain.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostVisibleState;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommentTest {

    private Comment comment;
    private User commentAuthor;

    @BeforeEach
    public void setUp() {
        User postAuthor = new User(1L, new UserInfo("user1", ""));
        Post post = new Post(1L, postAuthor, new PostContent("aaaaa"), PostVisibleState.PUBLIC);
        commentAuthor = new User(1L, new UserInfo("user1", ""));
        comment = new Comment(1L, post, commentAuthor, new CommentContent("aaaaa"));
    }

    @Test
    void givenCreated_whenLikes_thenGetLikeCounterIncrease() {
        //given
        User otherUser = new User(2L, new UserInfo("user2", ""));
        //when
        comment.getLike(otherUser);
        //then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCreated_whenSameUserLikes_thenErrorThrows() {
        //given
        //when, then
        assertThrows(IllegalArgumentException.class, () -> comment.getLike(commentAuthor));
    }

    @Test
    void givenCreated_whenDislikes_thenGetLikeCounterDecrease() {
        //given
        User otherUser = new User(2L, new UserInfo("user2", ""));
        //when
        comment.getLike(otherUser);
        comment.loseLike();
        //then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCreated_whenUpdateContent_thenUpdate() {
        //given
        String updateContent = "bbbbb";
        //when
        comment.updateComment(commentAuthor, updateContent);
        //then
        assertEquals(updateContent, comment.getContentMessage());
    }

    @Test
    void givenCreated_whenUpdateContentByOtherUser_thenErrorThrows() {
        //given
        User otherUser = new User(2L, new UserInfo("user2", ""));
        String updateContent = "bbbbb";
        //when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(otherUser, updateContent));
    }

    @Test
    void givenCreated_whenCompareWithAuthor_thenTrue() {
        assertTrue(comment.isAuthor(commentAuthor));
    }

    @Test
    void givenCreated_whenCompareWithUser_thenFalse() {
        //given
        User otherUser = new User(2L, new UserInfo("user2", ""));
        //then
        assertFalse(comment.isAuthor(otherUser));
    }

    @Test
    void givenCreated_thenGetLikeCountIsZero() {
        assertEquals(0, comment.getLikeCount());
    }

}
