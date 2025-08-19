package org.fastcampus.post.repository;

import java.util.ArrayList;
import java.util.List;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.post_queue.UserPostQueueQueryRepository;
import org.fastcampus.post.ui.dto.GetPostContentResDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class FakeUserPostQueryRepository implements UserPostQueueQueryRepository {

    private final FakeUserQueueRedisRepository fakeUserQueueRedisRepository;

    public FakeUserPostQueryRepository(FakeUserQueueRedisRepository fakeUserQueueRedisRepository) {
        this.fakeUserQueueRedisRepository = fakeUserQueueRedisRepository;
    }

    @Override
    public List<GetPostContentResDto> getPostList(Long userId, Long lastPostId) {
        List<PostEntity> postEntityList = fakeUserQueueRedisRepository.getPostListByUserId(userId);
        List<GetPostContentResDto> result = new ArrayList<>();
        postEntityList.forEach(
                it -> result.add(GetPostContentResDto.builder()
                        .id(it.getId())
                        .build())
        );
        return result;
    }
}
