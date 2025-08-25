package org.fastcampus.auth.domain.repository;

import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.auth.application.interfaces.EmailVerificationRepository;
import org.fastcampus.auth.domain.Email;
import org.fastcampus.auth.domain.repository.entity.EmailVerificationEntity;
import org.fastcampus.auth.domain.repository.jpa.JpaEmailVerificationRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {

    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;

    @Override
    @Transactional
    public void createEmailVerification(Email email, String token) {
        String emailValue = email.getEmail();
        Optional<EmailVerificationEntity> optional = jpaEmailVerificationRepository.findByEmail(emailValue);

        if (optional.isPresent()) {
            EmailVerificationEntity entity = optional.get();
            if (entity.isVerified()) {
                throw new IllegalArgumentException("email already exists");
            }
            entity.updateToken(token);
            return;
        }

        EmailVerificationEntity entity = new EmailVerificationEntity(emailValue, token);
        jpaEmailVerificationRepository.save(entity);
    }

    @Override
    @Transactional
    public void verifyEmail(Email email, String token) {
        String emailValue = email.getEmail();
        EmailVerificationEntity entity = jpaEmailVerificationRepository.findByEmail(emailValue)
                .orElseThrow(() -> new IllegalArgumentException("email not found"));

        if (!entity.hasSameToken(token)) {
            throw new IllegalArgumentException("token does not match");
        }

        if (entity.isVerified()) {
            throw new IllegalArgumentException("email already verified");
        }

        entity.verify();
    }
}
