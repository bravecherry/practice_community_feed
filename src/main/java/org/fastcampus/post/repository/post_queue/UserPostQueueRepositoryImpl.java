package org.fastcampus.post.repository.post_queue;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPostQueueRepositoryImpl implements UserPostQueueRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaPostRepository jpaPostRepository;
    private final UserQueueRedisRepository userQueueRedisRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity author = postEntity.getAuthor();
        List<Long> followerList = jpaUserRelationRepository.findFollowerIdList(author.getId());
        userQueueRedisRepository.publishPostToFollowingUserList(postEntity, followerList);
    }

    @Override
    @Transactional
    public void saveFollowerPost(Long userId, Long targetId) {
        List<PostEntity> postList = jpaPostRepository.findPostEntitiesByAuthorId(targetId);
        userQueueRedisRepository.publishPostListToFollowingUser(postList, userId);
    }

    @Override
    @Transactional
    public void deleteFollowerPost(Long userId, Long targetId) {
        userQueueRedisRepository.deleteUserFromFeed(userId, targetId);
    }
}
