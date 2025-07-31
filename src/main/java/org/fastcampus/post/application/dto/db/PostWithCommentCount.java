package org.fastcampus.post.application.dto.db;

import org.fastcampus.post.domain.Post;

public record PostWithCommentCount(Post post, Integer commentCount, Boolean liked) {

}
