package org.fastcampus.user.application;

import org.fastcampus.user.application.dto.UserFollowReqDto;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.domain.User;

public class UserRelationService {

    private final UserRelationRepository userRelationRepository;
    private final UserService userService;

    public UserRelationService(UserRelationRepository userRelationRepository, UserService userService) {
        this.userRelationRepository = userRelationRepository;
        this.userService = userService;
    }

    public void follow(UserFollowReqDto reqDto) {
        User user = userService.getUser(reqDto.userId());
        User targetUser = userService.getUser(reqDto.targetUserId());

        if (userRelationRepository.alreadyFollows(user, targetUser)) {
            throw new IllegalArgumentException();
        }

        user.follows(targetUser);
        userRelationRepository.save(user, targetUser);
    }

    public void unfollow(UserFollowReqDto reqDto) {
        User user = userService.getUser(reqDto.userId());
        User targetUser = userService.getUser(reqDto.targetUserId());

        if (!userRelationRepository.alreadyFollows(user, targetUser)) {
            throw new IllegalArgumentException();
        }

        user.unfollows(targetUser);
        userRelationRepository.delete(user, targetUser);
    }

}
