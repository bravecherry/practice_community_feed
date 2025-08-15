package org.fastcampus.user.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.common.repository.entity.TimeBasedEntity;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "community_user")
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class UserEntity extends TimeBasedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImage;
    private Integer followingCount;
    private Integer followerCounter;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<PostEntity> postList;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.profileImage = user.getProfileImageUrl();
        this.followingCount = user.getFollowingCount();
        this.followerCounter = user.getFollowerCount();
    }

    public User toUser() {
        return User.builder()
                .id(this.id)
                .info(new UserInfo(name, profileImage))
                .followingCounter(new PositiveIntegerCounter(followingCount))
                .followerCounter(new PositiveIntegerCounter(followerCounter))
                .build();
    }
}
