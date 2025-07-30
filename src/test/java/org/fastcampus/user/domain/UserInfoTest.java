package org.fastcampus.user.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class UserInfoTest {
    @Test
    void givenNameAndProfileImageUrl_whenCreated_thenThrowNothing() {
        //given
        String name = "AJ";
        String profileImageUrl = "image.url";
        //then
        assertDoesNotThrow(() -> new UserInfo(name, profileImageUrl));
    }

    @Test
    void givenBlankNameAndProfileImageUrl_whenCreated_thenThrowError() {
        //given
        String name = "";
        String profileImageUrl = "image.url";
        //then
        assertThrows(IllegalArgumentException.class, () -> new UserInfo(name, profileImageUrl));
    }

}
