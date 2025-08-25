package org.fastcampus.auth.domain.repository;

import lombok.RequiredArgsConstructor;
import org.fastcampus.auth.application.interfaces.UserAuthRepository;
import org.fastcampus.auth.domain.UserAuth;
import org.fastcampus.auth.domain.repository.entity.UserAuthEntity;
import org.fastcampus.auth.domain.repository.jpa.JpaUserAuthRepository;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final JpaUserAuthRepository jpaUserAuthRepository;
    private final UserRepository userRepository;

    @Override
    public UserAuth register(UserAuth userAuth, User user) {
        User savedUser = userRepository.save(user);
        UserAuthEntity userAuthEntity = new UserAuthEntity(userAuth, savedUser.getId());
        userAuthEntity = jpaUserAuthRepository.save(userAuthEntity);
        return userAuthEntity.toUserAuth();
    }

}
