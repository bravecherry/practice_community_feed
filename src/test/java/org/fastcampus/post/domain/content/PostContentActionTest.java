package org.fastcampus.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @NullAndEmptySource
    void givenCreated_whenContentLengthShorterThanMin_thenThrowsError(String content) {
        //then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenCreated_whenContentLengthLongerThanMax_thenThrowsError() {
        //given
        String content = "a".repeat(501);
        //then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenCreated_whenKoreanContentLengthLongerThanMax_thenThrowsError() {
        //given
        String content = "뷁".repeat(501);
        //then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 곩, 삵, 슳"})
    void givenCreated_whenContentUsingParameterTestLengthLongerThanMax_thenThrowsError(String str) {
        //given
        String content = str.repeat(501);
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

    @ParameterizedTest
    @NullAndEmptySource
    void givenEmptyContent_whenCreated_thenThrowsException(String emptyContent) {
        //given
        PostContent postContent = new PostContent("content");
        //then
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(emptyContent));
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
