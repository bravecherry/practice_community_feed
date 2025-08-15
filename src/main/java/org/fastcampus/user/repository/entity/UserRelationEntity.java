package org.fastcampus.user.repository.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.repository.entity.TimeBasedEntity;
import org.fastcampus.user.repository.entity.UserRelationEntity.UserRelationIdEntity;

@Entity
@Table(name = "user_community_relation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@IdClass(UserRelationIdEntity.class)
public class UserRelationEntity extends TimeBasedEntity {

    @Id
    private Long followerUserId;
    @Id
    private Long followingUserId;

    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public class UserRelationIdEntity {
        private Long followerUserId;
        private Long followingUserId;
    }
}
