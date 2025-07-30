package org.fastcampus.user.domain;

public class UserProfileUpdateDto {
    private final String username;
    private final String profileImageUrl;

    public UserProfileUpdateDto(String username, String profileImageUrl) {
        this.username = username;
        this.profileImageUrl = profileImageUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
