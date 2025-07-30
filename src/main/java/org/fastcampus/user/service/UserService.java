package org.fastcampus.user.service;

import java.util.List;
import java.util.Optional;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.fastcampus.user.domain.UserProfileReadDto;
import org.fastcampus.user.domain.UserProfileUpdateDto;
import org.fastcampus.user.domain.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserProfileReadDto getUserProfile(long userId) {
        Optional<User> optional = userRepository.findById(userId);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException();
        }
        User user = optional.get();
        return new UserProfileReadDto(user);
    }

    public void updateUserInfo(long userId, UserProfileUpdateDto dto) {
        Optional<User> optional = userRepository.findById(userId);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException();
        }
        User user = optional.get();
        UserInfo userInfo = user.getUserInfo();
        userInfo.setName(dto.getUsername());
        userInfo.setProfileImageUrl(dto.getProfileImageUrl());
        userRepository.save(user);
    }

    public List<User> getFollwerList(User user) {
        return List.of();
    }

    public List<User> getFollowingList(User user) {
        return List.of();
    }

    public void follow(User user, User targetUser) {
        user.follow(targetUser);
    }

    public void unfollow(User user, User targetUser) {
        user.unfollow(targetUser);
    }

}
