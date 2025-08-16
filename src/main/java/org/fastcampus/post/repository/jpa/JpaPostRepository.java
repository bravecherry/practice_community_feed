package org.fastcampus.post.repository.jpa;

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
            + "set p.likeCount = :likeCount,"
            + "p.modDtm=now() "
            + "where p.id=:postId")
    void updateLikeCount(Long postId, Integer likeCount);
}
