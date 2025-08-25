package org.fastcampus.acceptance.auth;

import static org.fastcampus.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static org.fastcampus.acceptance.steps.SignUpAcceptanceSteps.requestVerifyEmail;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.fastcampus.acceptance.utils.AcceptanceTestTemplate;
import org.fastcampus.auth.application.dto.SendEmailReqDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SignUpAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "test@email.com";

    @BeforeEach
    void setUp() {
        this.cleanUp();
    }

    @Test
    void givenEmail_whenSendEmail_thenVerificationCodeSaved() {
        //given
        SendEmailReqDto reqDto = new SendEmailReqDto(email);
        //when
        Integer code = requestSendEmail(reqDto);
        //then
        String token = getEmailToken(email);
        assertNotNull(token);
        assertEquals(0, code);

    }

    @Test
    void givenInvalidEmail_whenSendEmail_thenVerificationTokenNotSaved() {
        //given
        SendEmailReqDto reqDto = new SendEmailReqDto("notAnEmail");
        //when
        Integer code = requestSendEmail(reqDto);
        //then
        assertEquals(400, code);
    }

    @Test
    void givenSendEmail_whenVerifyEmail_thenEmailVerified() {
        //given
        requestSendEmail(new SendEmailReqDto(email));
        //when
        String token = getEmailToken(email);
        Integer code = requestVerifyEmail(email, token);
        //then
        boolean verified = isEmailVerified(email);
        assertEquals(0, code);
        assertTrue(verified);
    }

    @Test
    void givenSendEmail_whenVerifyEmailWithWrongToken_thenEmailNotVerified() {
        //given
        requestSendEmail(new SendEmailReqDto(email));
        //when
        Integer code = requestVerifyEmail(email, "wrong token");
        //then
        boolean verified = isEmailVerified(email);
        assertEquals(400, code);
        assertFalse(verified);
    }

    /**
     * 실패
     */
    @Test
    void givenSendEmailVerified_whenVerifyAgain_thenThrowException() {
        //given
        requestSendEmail(new SendEmailReqDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email, token);
        //when
        Integer code = requestVerifyEmail(email, token);
        //then
        assertEquals(400, code);
    }

    @Test
    void givenSendEmail_whenVerifyEmailWithWrongEmail_thenThrowException() {
        //given
        requestSendEmail(new SendEmailReqDto(email));
        //when
        Integer code = requestVerifyEmail("wrong email", "useless token");
        //then
        assertEquals(400, code);
    }

}
