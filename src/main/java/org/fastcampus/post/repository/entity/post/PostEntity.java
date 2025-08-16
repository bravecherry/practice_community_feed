package org.fastcampus.post.repository.entity.post;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.common.repository.entity.TimeBasedEntity;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostVisibleState;
import org.fastcampus.post.repository.convert.PostVisibleStateConverter;
import org.fastcampus.user.repository.entity.UserEntity;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostEntity extends TimeBasedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Integer likeCount;

    @ColumnDefault("0")
    private int commentCount;

    @ManyToOne
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    @Convert(converter = PostVisibleStateConverter.class)
    private PostVisibleState state;

    public PostEntity(Post post) {
        this.id = post.getId();
        this.content = post.getContentMessage();
        this.likeCount = post.getLikeCount();
        this.author = new UserEntity(post.getAuthor());
    }

    public Post toPost() {
        return Post.builder()
                .id(id)
                .content(new PostContent(content))
                .likeCounter(new PositiveIntegerCounter(likeCount))
                .author(author.toUser())
                .build();
    }

}
