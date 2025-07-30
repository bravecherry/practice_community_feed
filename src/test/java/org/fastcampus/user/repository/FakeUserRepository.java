package org.fastcampus.user.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;

public class FakeUserRepository implements UserRepository {

    private final Map<Long, User> users = new HashMap<>();

    @Override
    public User save(User user) {
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
        }
        long id = users.size() + 1;
        User newUser = new User(id, user.getUserInfo());
        users.put(id, newUser);
        return newUser;
    }

    @Override
    public Optional<User> findById(long userId) {
        return Optional.ofNullable(users.get(userId));
    }
}
