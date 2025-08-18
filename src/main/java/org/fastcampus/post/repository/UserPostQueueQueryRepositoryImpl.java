package org.fastcampus.post.repository;

import static org.fastcampus.post.repository.entity.like.QLikeEntity.likeEntity;
import static org.fastcampus.post.repository.entity.post.QPostEntity.postEntity;
import static org.fastcampus.post.repository.entity.post.QUserPostQueueEntity.userPostQueueEntity;
import static org.fastcampus.user.repository.entity.QUserEntity.userEntity;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.repository.post_queue.UserPostQueueQueryRepository;
import org.fastcampus.post.ui.dto.GetPostContentResDto;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<GetPostContentResDto> getPostList(Long userId, Long lastPostId) {
        return queryFactory.select(
                Projections.fields(
                        GetPostContentResDto.class,
                        postEntity.id.as("id"),
                        postEntity.content.as("content"),
                        userEntity.id.as("userId"),
                        userEntity.name.as("userName"),
                        userEntity.profileImage.as("userProfileImage"),
                        postEntity.regDtm.as("createdAt"),
                        postEntity.modDtm.as("updatedAt"),
                        postEntity.commentCount.as("commentCount"),
                        postEntity.likeCount.as("likeCount"),
                        likeEntity.isNotNull().as("isLikedByMe")
                )
        ).from(userPostQueueEntity)
                .join(postEntity).on(postEntity.id.eq(userPostQueueEntity.postId))
                .join(userEntity).on(userEntity.id.eq(userPostQueueEntity.authorId))
                .leftJoin(likeEntity).on(hasLike(userId))
                .where(
                        userPostQueueEntity.userId.eq(userId),
                        hasLastData(lastPostId)
                )
                .orderBy(userPostQueueEntity.postId.desc())
                .limit(20)
                .fetch()
                ;
    }

    private BooleanExpression hasLastData(Long lastPostId) {
        if (lastPostId == null) {
            return null;
        }
        return postEntity.id.lt(lastPostId);
    }

    private BooleanExpression hasLike(Long userId) {
        if (userId == null) {
            return null;
        }
        return postEntity.id
                .eq(likeEntity.id.targetId)
                .and(likeEntity.id.targetType.eq("POST"))
                .and(likeEntity.id.userId.eq(userId));
    }
}
