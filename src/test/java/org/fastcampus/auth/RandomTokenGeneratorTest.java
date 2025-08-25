package org.fastcampus.auth;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.fastcampus.auth.domain.RandomTokenGenerator;
import org.junit.jupiter.api.Test;

public class RandomTokenGeneratorTest {

    @Test
    void testGenerate() {
        //when
        String token = RandomTokenGenerator.generateToken();
        //then
        assertNotNull(token);
        assertEquals(16, token.length());
    }

    @Test
    void testGenerate2() {
        //when
        String token = RandomTokenGenerator.generateToken();
        //then
        assertNotNull(token);
        assertTrue(token.matches("[a-zA-Z0-9]{16}"));
    }


    @Test
    void testGenerate3() {
        //when
        String token1 = RandomTokenGenerator.generateToken();
        String token2 = RandomTokenGenerator.generateToken();
        //then
        assertNotNull(token1);
        assertNotNull(token2);
        assertNotEquals(token1, token2);
    }

}
