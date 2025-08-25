package org.fastcampus.auth.application;

import lombok.RequiredArgsConstructor;
import org.fastcampus.auth.application.dto.SendEmailReqDto;
import org.fastcampus.auth.application.interfaces.EmailVerificationRepository;
import org.fastcampus.auth.application.interfaces.SendEmailRepository;
import org.fastcampus.auth.domain.Email;
import org.fastcampus.auth.domain.RandomTokenGenerator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final SendEmailRepository sendEmailRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    public void sendEmail(SendEmailReqDto reqDto) {
        Email email = Email.createEmail(reqDto.email());
        String token = RandomTokenGenerator.generateToken();

        sendEmailRepository.sendEmail(email, token);
        emailVerificationRepository.createEmailVerification(email, token);
    }

    public void verifyEmail(String email, String token) {
        emailVerificationRepository.verifyEmail(Email.createEmail(email), token);
    }

}
