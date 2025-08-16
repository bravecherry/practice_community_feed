package org.fastcampus.post.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.application.interfaces.ContentRelationRepository;
import org.fastcampus.post.domain.common.ContentAction;
import org.fastcampus.post.repository.entity.like.LikeEntity;
import org.fastcampus.post.repository.entity.like.LikeTarget;
import org.fastcampus.post.repository.jpa.JpaCommentRepository;
import org.fastcampus.post.repository.jpa.JpaLikeRepository;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.fastcampus.user.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements ContentRelationRepository {

    private final JpaLikeRepository jpaLikeRepository;
    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;

    // 기본적으로 save 메소드 호출 시 merge 가 발생하는데 추가 전 조회를 진행한다.
    // 이때 조회하지 않고 바로 넣을 수 있도록 entityManager 애서 관리
    // 다만 이렇게 하기 위해서는 어플리케이션 단에서 중복 확인을 반드시 해야 한다.(duplicate 에러 발생)
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public boolean alreadyLiked(ContentAction contentAction, User user) {
        LikeEntity likeEntity = new LikeEntity(contentAction, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(ContentAction contentAction, User user) {
        LikeEntity like = new LikeEntity(contentAction, user);
        entityManager.persist(like); // 불필요한 조회 없이 저장
        LikeTarget target = contentAction.getTarget();
        if (target == LikeTarget.POST) {
            jpaPostRepository.updateLikeCount(contentAction.getId(), contentAction.getLikeCount());
        } else if (target == LikeTarget.COMMENT) {
            jpaCommentRepository.updateLikeCount(contentAction.getId(), contentAction.getLikeCount());
        }
    }

    @Override
    @Transactional
    public void dislike(ContentAction contentAction, User user) {
        LikeEntity like = new LikeEntity(contentAction, user);
        jpaLikeRepository.delete(like);
        LikeTarget target = contentAction.getTarget();
        if (target == LikeTarget.POST) {
            jpaPostRepository.updateLikeCount(contentAction.getId(), contentAction.getLikeCount());
        } else if (target == LikeTarget.COMMENT) {
            jpaCommentRepository.updateLikeCount(contentAction.getId(), contentAction.getLikeCount());
        }
    }

}
