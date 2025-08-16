package org.fastcampus.user.application;

import lombok.RequiredArgsConstructor;
import org.fastcampus.user.application.dto.CreateUserReqDto;
import org.fastcampus.user.application.dto.GetUserResDto;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.fastcampus.user.domain.UserProfileUpdateDto;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(CreateUserReqDto reqDto) {
        UserInfo userInfo = new UserInfo(reqDto.getName(), reqDto.getProfileImageUrl());
        return userRepository.save(new User(null, userInfo));
    }

    public GetUserResDto getUserProfile(Long userId) {
        User user = userRepository.findById(userId);
        return new GetUserResDto(user);
    }

    public void updateUserInfo(Long userId, UserProfileUpdateDto dto) {
        User user = getUser(userId);
        user.setName(dto.getUsername());
        user.setProfileImageUrl(dto.getProfileImageUrl());
        userRepository.save(user);
    }

}
