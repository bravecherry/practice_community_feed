package org.fastcampus.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.fastcampus.auth.domain.Password;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;

class PasswordTest {

    @Test
    void givenPassword_whenMatchesSamePassword_thenReturnTrue() {
        //given
        Password password = Password.createEncryptedPassword("password");
        //when
        boolean result = password.matches("password");
        //then
        assertTrue(result);
    }

    @Test
    void givenPassword_whenMatchesDiffPassword_thenReturnFalse() {
        //given
        Password password = Password.createEncryptedPassword("password123");
        //when
        boolean result = password.matches("password246");
        //then
        assertFalse(result);
    }

    @ParameterizedTest
    @EmptySource
    void givenNullPassword_thenThrowsException(String password) {
        assertThrows(IllegalArgumentException.class, () -> Password.createEncryptedPassword(password));
    }
}
