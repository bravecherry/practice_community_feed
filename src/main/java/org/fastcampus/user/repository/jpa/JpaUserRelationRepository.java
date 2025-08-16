package org.fastcampus.user.repository.jpa;

import java.util.List;
import org.fastcampus.user.repository.entity.UserRelationEntity;
import org.fastcampus.user.repository.entity.UserRelationEntity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {

    @Query(value = "select ur.followerUserId "
            + "from UserRelationEntity ur "
            + "where ur.followingUserId = :id")
    List<Long> findFollowerIdList(Long id);

}
