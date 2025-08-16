package org.fastcampus.post.repository;

import lombok.RequiredArgsConstructor;
import org.fastcampus.post.application.interfaces.ContentRelationRepository;
import org.fastcampus.post.domain.common.ContentAction;
import org.fastcampus.post.repository.entity.like.LikeEntity;
import org.fastcampus.post.repository.jpa.JpaLikeRepository;
import org.fastcampus.user.domain.User;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements ContentRelationRepository {

    private final JpaLikeRepository jpaLikeRepository;

    @Override
    public boolean alreadyLiked(ContentAction contentAction, User user) {
        LikeEntity likeEntity = new LikeEntity(contentAction, user);
        return jpaLikeRepository.findById(likeEntity.getId()).isPresent();
    }

    @Override
    public void like(ContentAction contentAction, User user) {
        LikeEntity like = new LikeEntity(contentAction, user);
        jpaLikeRepository.save(like);
    }

    @Override
    public void dislike(ContentAction contentAction, User user) {
        LikeEntity like = new LikeEntity(contentAction, user);
        jpaLikeRepository.delete(like);
    }

}
