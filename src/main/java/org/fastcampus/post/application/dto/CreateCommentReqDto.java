package org.fastcampus.post.application.dto;

public record CreateCommentReqDto(Long postId, Long userId, String content) {

}
