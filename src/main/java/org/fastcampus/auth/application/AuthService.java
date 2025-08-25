package org.fastcampus.auth.application;

import lombok.RequiredArgsConstructor;
import org.fastcampus.auth.application.dto.CreateUserAuthReqDto;
import org.fastcampus.auth.application.interfaces.EmailVerificationRepository;
import org.fastcampus.auth.application.interfaces.UserAuthRepository;
import org.fastcampus.auth.domain.Email;
import org.fastcampus.auth.domain.UserAuth;
import org.fastcampus.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserAuthRepository userAuthRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    public Long register(CreateUserAuthReqDto reqDto) {
        Email email = Email.createEmail(reqDto.email());

        if (!emailVerificationRepository.isEmailVerified(email)) {
            throw new IllegalArgumentException("not verified email");
        }

        UserAuth userAuth = new UserAuth(reqDto.email(), reqDto.password(), reqDto.role());
        User user = new User(reqDto.name(), reqDto.profileImageUrl());
        userAuth = userAuthRepository.register(userAuth, user);
        return userAuth.getUserId();
    }

}
