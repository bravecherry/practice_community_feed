package org.fastcampus.user.domain;

import lombok.Getter;

public class UserProfileReadDto {
    @Getter
    private final Long id;
    private final String username;
    private final String profileImageUrl;
    private final Integer followerCount;
    private final Integer followingCount;

    public UserProfileReadDto(User user) {
        this.id = user.getId();
        this.username = user.getName();
        this.profileImageUrl = user.getProfileImageUrl();
        this.followerCount = user.getFollowerCount();
        this.followingCount = user.getFollowingCount();
    }

}
