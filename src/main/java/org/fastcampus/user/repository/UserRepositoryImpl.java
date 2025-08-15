package org.fastcampus.user.repository;

import lombok.RequiredArgsConstructor;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity userEntity = jpaUserRepository.save(new UserEntity(user));
        return userEntity.toUser();
    }

    @Override
    public User findById(long userId) {
        UserEntity userEntity = jpaUserRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        return userEntity.toUser();
    }
}
