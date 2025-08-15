package org.fastcampus.user.repository.jpa;

import java.util.List;
import org.fastcampus.user.application.dto.GetUserListResDto;
import org.fastcampus.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserListRepository extends JpaRepository<UserEntity, Long> {

    // JPQL
    @Query(value = "select new org.fastcampus.user.application.dto.GetUserListResDto(u.name, u.profileImage)"
            + "from UserRelationEntity ur "
            + "join UserEntity u on u.id = ur.followingUserId "
            + "where ur.followingUserId = :userId")
    List<GetUserListResDto> getFollwingUserList(Long userId);

    // JPQL
    @Query(value = "select new org.fastcampus.user.application.dto.GetUserListResDto(u.name, u.profileImage)"
            + "from UserRelationEntity ur "
            + "join UserEntity u on u.id = ur.followerUserId "
            + "where ur.followerUserId = :userId")
    List<GetUserListResDto> getFollwerUserList(Long userId);

}
