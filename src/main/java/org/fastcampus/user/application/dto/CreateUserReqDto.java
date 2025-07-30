package org.fastcampus.user.application.dto;

public class CreateUserReqDto {

    private String name;
    private String profileImageUrl;

    public CreateUserReqDto(String name, String profileImageUrl) {
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
