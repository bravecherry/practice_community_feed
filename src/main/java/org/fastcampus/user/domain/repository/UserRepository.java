package org.fastcampus.user.domain.repository;

import java.util.Optional;
import org.fastcampus.user.domain.User;

public interface UserRepository {
    void save(User user);

    Optional<User> findById(long userId);
}
