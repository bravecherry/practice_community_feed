package org.fastcampus.auth.domain.repository;

import lombok.RequiredArgsConstructor;
import org.fastcampus.auth.application.interfaces.SendEmailRepository;
import org.fastcampus.auth.domain.Email;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SendEmailRepositoryImpl implements SendEmailRepository {

    @Override
    public void sendEmail(Email email, String randomToken) {

    }
}
