package org.fastcampus.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PostContentActionTest {

    @Test
    void givenContent_thenCreated() {
        //given
        String content = "aaaaaaa";
        //when
        PostContent postContent = new PostContent(content);
        //then
        assertEquals(content, postContent.getContent());
    }

    @Test
    void givenCreated_whenContentLengthShorterThanMin_thenThrowsError() {
        //given
        String content = "";
        //then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenCreated_whenContentLengthLongerThanMax_thenThrowsError() {
        //given
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 501; i++) {
            stringBuilder.append("a");
        }
        String content = stringBuilder.toString();
        //then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenContent_whenUpdate_thenUpdated() {
        //given
        String content = "aaaaaaa";
        PostContent postContent = new PostContent("content");
        //when
        postContent.updateContent(content);
        //then
        assertEquals(content, postContent.getContent());
    }

    @Test
    void givenContent_whenContentNotValid_thenThrowsException() {
        //given
        String content = null;
        PostContent postContent = new PostContent("content");
        //then
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(content));
    }

    @Test
    void givenCreated_thenIsEditedFalse() {
        //given
        PostContent postContent = new PostContent("content");
        //then
        assertFalse(postContent.isEdited());
    }

    @Test
    void givenCreated_thenDateTimeNotNull() {
        //given
        PostContent postContent = new PostContent("content");
        //then
        assertNotNull(postContent.getRegDtm());
        assertNull(postContent.getModDtm());
    }

}
