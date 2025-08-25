package org.fastcampus.auth.application.dto;

public record CreateUserAuthReqDto(String email, String password, String role, String name, String profileImageUrl) {

}
