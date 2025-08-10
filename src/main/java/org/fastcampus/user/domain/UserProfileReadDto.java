package org.fastcampus.user.domain;

public class UserProfileReadDto {
    private final Long id;
    private final String username;
    private final String profileImageUrl;
    private final Integer followerCount;
    private final Integer followingCount;

    public UserProfileReadDto(User user) {
        this.id = user.getId();
        UserInfo userInfo = user.getUserInfo();
        this.username = userInfo.getName();
        this.profileImageUrl = userInfo.getProfileImageUrl();
        this.followerCount = user.getFollowerCount();
        this.followingCount = user.getFollowingCount();
    }

    public Long getId() {
        return id;
    }
}
