package org.fastcampus.post.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.post_queue.UserQueueRedisRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class FakeUserQueueRedisRepository implements UserQueueRedisRepository {

    private final Map<Long, Set<PostEntity>> queue = new HashMap<>();

    @Override
    public void publishPostToFollowingUserList(PostEntity postEntity, List<Long> userIdList) {
        for (Long userId : userIdList) {
            queue.computeIfAbsent(userId, k -> new HashSet<>()).add(postEntity);
        }

    }

    @Override
    public void publishPostListToFollowingUser(List<PostEntity> postEntityList, Long userId) {
        queue.computeIfAbsent(userId, k -> new HashSet<>()).addAll(postEntityList);
    }

    @Override
    public void deleteUserFromFeed(Long userId, Long authorId) {
        queue.get(userId).removeIf(post -> post.getAuthor().getId().equals(authorId));
    }

    public List<PostEntity> getPostListByUserId(Long userId) {
        return queue.get(userId).stream().toList();
    }
}
