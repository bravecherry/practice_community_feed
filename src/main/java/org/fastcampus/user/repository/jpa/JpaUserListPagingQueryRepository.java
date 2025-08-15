package org.fastcampus.user.repository.jpa;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.user.application.dto.GetUserListResDto;
import org.fastcampus.user.repository.entity.QUserEntity;
import org.fastcampus.user.repository.entity.QUserRelationEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaUserListPagingQueryRepository {

    private static final QUserEntity user = QUserEntity.userEntity;
    private static final QUserRelationEntity userRelation = QUserRelationEntity.userRelationEntity;
    private final JPAQueryFactory jpaQueryFactory;

    public List<GetUserListResDto> getFollowerList(Long userId, Long lastFollowerId) {
        return jpaQueryFactory.select(
                        Projections.fields(
                                GetUserListResDto.class,
                                hasLastData(lastFollowerId)
                        )
                )
                .from(userRelation)
                .join(user).on(user.id.eq(userRelation.followingUserId))
                .where(
                        userRelation.followerUserId.eq(userId),
                        hasLastData(lastFollowerId)
                )
                .orderBy(user.id.desc())
                .limit(20)
                .fetch();

    }

    private BooleanExpression hasLastData(Long lastFollowerId) {
        if (lastFollowerId == null) {
            return null;
        }
        return user.id.lt(lastFollowerId);
    }
}
