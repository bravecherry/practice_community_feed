package org.fastcampus.post.repository.jpa;

import java.util.List;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {


    @Modifying
    @Query(value = "update PostEntity p "
            + "set p.content = :#{#postEntity.getContent()},"
            + "p.state = :#{#postEntity.state},"
            + "p.modDtm = now() "
            + "where p.id = :#{#postEntity.id}}")
    void updatePost(PostEntity postEntity);

    @Modifying
    @Query(value = "update PostEntity p "
            + "set p.likeCount = p.likeCount + :likeCount,"
            + "p.modDtm=now() "
            + "where p.id=:postId")
    void updateLikeCount(Long postId, Integer likeCount);

    @Query(value = "update PostEntity p "
            + "set p.commentCount = p.commentCount + 1 "
            + "where p.id = :id")
    void increaseCommentCount(Long id);

    @Query(value = "select p.id from PostEntity p where p.author.id = :userId")
    List<Long> findPostIdListByAuthorId(Long userId);
}
