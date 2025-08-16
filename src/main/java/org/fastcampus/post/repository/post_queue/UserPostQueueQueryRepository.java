package org.fastcampus.post.repository.post_queue;

import java.util.List;
import org.fastcampus.post.ui.dto.GetPostContentResDto;

public interface UserPostQueueQueryRepository {
    List<GetPostContentResDto> getPostList(Long userId, Long lastPostId);
}
