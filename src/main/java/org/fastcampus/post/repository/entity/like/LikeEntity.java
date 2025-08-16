package org.fastcampus.post.repository.entity.like;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.repository.entity.TimeBasedEntity;
import org.fastcampus.post.domain.common.ContentAction;
import org.fastcampus.user.domain.User;

@Entity
@Table(name = "content_like")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LikeEntity extends TimeBasedEntity {

    @EmbeddedId
    private LikeIdEntity id;

    public LikeEntity(ContentAction contentAction, User likedUser) {
        this.id = new LikeIdEntity(contentAction.getId(), likedUser.getId(), LikeTarget.POST.name());
    }

    @Embeddable
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LikeIdEntity {
        private Long targetId;
        private Long userId;
        private String targetType;
    }
}
