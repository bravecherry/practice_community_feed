package org.fastcampus.user.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;

public class FakeUserRepository implements UserRepository {

    private final Map<Long, User> users = new HashMap<>();

    @Override
    public User save(User user) {
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            return user;
        }
        long id = users.size() + 1;
        User newUser = new User(id, new UserInfo(user.getName(), user.getProfileImageUrl()));
        users.put(id, newUser);
        return newUser;
    }

    @Override
    public User findById(long userId) {
        return users.get(userId);
    }
}
