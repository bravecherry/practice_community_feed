package org.fastcampus.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class CommentContentActionTest {

    @Test
    void givenContent_thenCreated() {
        //given
        String content = "aaaaaaa";
        //when
        CommentContent commentContent = new CommentContent(content);
        //then
        assertEquals(content, commentContent.getContent());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenCreated_whenContentLengthShorterThanMin_thenThrowsError(String str) {
        //then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(str));
    }

    @Test
    void givenCreated_whenContentLengthLongerThanMax_thenThrowsError() {
        //given
        String content = "a".repeat(101);
        //then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 삵, 슳, 쉙, 뛣"})
    void givenCreated_whenKoreanContentLengthLongerThanMax_thenThrowsError() {
        //given
        String content = "a".repeat(101);
        //then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @Test
    void givenContent_whenUpdate_thenUpdated() {
        //given
        String content = "aaaaaaa";
        CommentContent commentContent = new CommentContent("content");
        //when
        commentContent.updateContent(content);
        //then
        assertEquals(content, commentContent.getContent());
    }

    @Test
    void givenContent_whenContentNotValid_thenThrowsException() {
        //given
        String content = null;
        CommentContent commentContent = new CommentContent("content");
        //then
        assertThrows(IllegalArgumentException.class, () -> commentContent.updateContent(content));
    }

    @Test
    void givenCreated_thenIsEditedFalse() {
        //given
        CommentContent commentContent = new CommentContent("content");
        //then
        assertFalse(commentContent.isEdited());
    }

    @Test
    void givenCreated_thenDateTimeNotNull() {
        //given
        CommentContent commentContent = new CommentContent("content");
        //then
        assertNotNull(commentContent.getRegDtm());
        assertNull(commentContent.getModDtm());
    }

}
