package org.fastcampus.post.repository.post_queue;

import org.fastcampus.post.repository.entity.post.PostEntity;

/**
 * 서비스 레이어로부터 분리된 로직
 */
public interface UserPostQueueRepository {

    /**
     * 포스트 게시할 때 팔로워들에 파드 추가
     */
    void publishPost(PostEntity postEntity);

    /**
     * 특정 사용자를 팔로우할 때 사용자 포스트를 피드에 추가
     */
    void saveFollowerPost(Long userId, Long targetId);

    /**
     * 특정 사용자를 팔로우 취소할 때 사용자 포스트를 피드에서 제거
     */
    void deleteFollowerPost(Long userId, Long targetId);
}
