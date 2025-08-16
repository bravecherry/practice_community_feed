package org.fastcampus.post.repository.post_queue;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.entity.post.UserPostQueueEntity;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.fastcampus.post.repository.jpa.JpaUserPostQueueRepository;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPostQueueRepositoryImpl implements UserPostQueueRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;
    private final JpaPostRepository jpaPostRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity author = postEntity.getAuthor();
        List<Long> followerList = jpaUserRelationRepository.findFollowerIdList(author.getId());
        List<UserPostQueueEntity> list = followerList.stream()
                .map(userId -> new UserPostQueueEntity(userId, postEntity.getId(), postEntity.getAuthor().getId()))
                .toList();
        jpaUserPostQueueRepository.saveAll(list);
    }

    @Override
    @Transactional
    public void saveFollowerPost(Long userId, Long targetId) {
        List<Long> postIdList = jpaPostRepository.findPostIdListByAuthorId(targetId);
        List<UserPostQueueEntity> list = postIdList.stream()
                .map(postId -> new UserPostQueueEntity(userId, postId, targetId))
                .toList();
        jpaUserPostQueueRepository.saveAll(list);
    }

    @Override
    @Transactional
    public void deleteFollowerPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
    }
}
