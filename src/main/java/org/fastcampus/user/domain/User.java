package org.fastcampus.user.domain;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.domain.PositiveIntegerCounter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Getter
    private Long id;
    private UserInfo info;
    private PositiveIntegerCounter followingCounter;
    private PositiveIntegerCounter followerCounter;

    public User(Long id, UserInfo info) {
        if (info == null) {
            throw new IllegalArgumentException("userInfo cannot be null");
        }

        this.id = id;
        this.info = info;
        this.followingCounter = new PositiveIntegerCounter();
        this.followerCounter = new PositiveIntegerCounter();
    }

    public void follows(User targetUser) {
        if (targetUser.equals(this)) {
            throw new IllegalArgumentException();
        }
        followingCounter.increase();
        targetUser.increaseFollowersCount();
    }

    public void unfollows(User targetUser) {
        if (targetUser.equals(this)) {
            throw new IllegalArgumentException();
        }
        followingCounter.decrease();
        targetUser.decreaseFollowersCount();
    }

    private void increaseFollowersCount() {
        followerCounter.increase();
    }

    private void decreaseFollowersCount() {
        followerCounter.decrease();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public int getFollowingCount() {
        return this.followingCounter.getCount();
    }

    public int getFollowerCount() {
        return this.followerCounter.getCount();
    }

    public String getName() {
        return this.info.getName();
    }

    public String getProfileImageUrl() {
        return this.info.getProfileImageUrl();
    }

    public void setName(String username) {
        this.info.setName(username);
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.info.setProfileImageUrl(profileImageUrl);
    }
}
