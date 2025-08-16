package org.fastcampus.post.repository.jpa;

import java.util.List;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query(value = "update CommentEntity c "
            + "set c.content = :#{#commentEntity.content}, "
            + "c.modDtm = now() "
            + "where c.id = :#{#commentEntity.id}")
    void update(CommentEntity commentEntity);

    @Modifying
    @Query(value = "update CommentEntity c "
            + "set c.likeCount = :likeCount,"
            + "c.modDtm = now() "
            + "where c.id = :id")
    void updateLikeCount(Long id, Integer likeCount);

    @Query(value = "select c from CommentEntity c where c.post = :post")
    List<CommentEntity> findByPostId(Post post);
}
