package org.fastcampus.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.auth.domain.Email;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class EmailTest {

    @ParameterizedTest
    @NullAndEmptySource
    void givenEmailIsEmpty_whenCreate_thenThrowsError(String email) {
        assertThrows(IllegalArgumentException.class, Email::new);
    }

    @ParameterizedTest
    @ValueSource(strings = {"iAmEmail", "i.am.email", "ㄴㅏ는이메일이다"})
    void givenInvalidEmail_whenCreate_thenThrowsError(String email) {
        assertThrows(IllegalArgumentException.class, Email::new);
    }

    @ParameterizedTest
    @ValueSource(strings = {"i.am@email.com"})
    void givenValidEmail_whenCreate_thenThrowsError(String emailValue) {

        //when
        Email email = new Email(emailValue);

        //then
        assertEquals(emailValue, email.getEmail());
    }

}
