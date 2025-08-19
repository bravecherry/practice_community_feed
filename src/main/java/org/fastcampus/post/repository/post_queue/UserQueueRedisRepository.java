package org.fastcampus.post.repository.post_queue;

import java.util.List;
import org.fastcampus.post.repository.entity.post.PostEntity;

public interface UserQueueRedisRepository {
    void publishPostToFollowingUserList(PostEntity postEntity, List<Long> userIdList);
    void publishPostListToFollowingUser(List<PostEntity> postEntityList, Long userId);
    void deleteUserFromFeed(Long userId, Long authorId);
}
