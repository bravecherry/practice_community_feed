package org.fastcampus.auth.application.interfaces;

import org.fastcampus.auth.domain.Email;

public interface SendEmailRepository {
    public void sendEmail(Email email, String randomToken);
}
