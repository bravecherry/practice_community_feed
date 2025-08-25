package org.fastcampus.acceptance.auth;

import static org.fastcampus.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        String token = getEmailToken(email);
        assertNull(token);
        assertEquals(400, code);
    }

}
