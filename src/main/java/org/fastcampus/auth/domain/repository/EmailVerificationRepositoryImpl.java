package org.fastcampus.auth.domain.repository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.auth.application.EmailService;
import org.fastcampus.auth.application.interfaces.EmailVerificationRepository;
import org.fastcampus.auth.domain.Email;
import org.fastcampus.auth.domain.repository.entity.EmailVerificationEntity;
import org.fastcampus.auth.domain.repository.jpa.JpaEmailVerificationRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {

    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;
    private final EmailService emailService;

    @Override
    public void createEmailVerification(Email email, String token) {
        String emailValue = email.getEmail();
        Optional<EmailVerificationEntity> entity = jpaEmailVerificationRepository.findByEmail(emailValue);

        if (entity.isPresent()) {
            EmailVerificationEntity entity1 = entity.get();
            if (entity1.isVerified()) {
                throw new IllegalArgumentException("email already exists");
            }
            entity1.updateToken(token);
            return;
        }

        EmailVerificationEntity entity3 = new EmailVerificationEntity(emailValue, token);
        jpaEmailVerificationRepository.save(entity3);
    }
}
