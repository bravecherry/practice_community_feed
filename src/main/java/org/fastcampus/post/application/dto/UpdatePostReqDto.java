package org.fastcampus.post.application.dto;

import org.fastcampus.post.domain.content.PostVisibleState;

public record UpdatePostReqDto(String content, Long userId, PostVisibleState visibleState) {

}
