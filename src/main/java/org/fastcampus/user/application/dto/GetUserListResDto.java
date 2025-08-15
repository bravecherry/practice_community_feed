package org.fastcampus.user.application.dto;

import lombok.Getter;

@Getter
public class GetUserListResDto {

    private final String name;
    private final String profileImage;

    public GetUserListResDto(String name, String profileImage) {
        this.name = name;
        this.profileImage = profileImage;
    }

}
