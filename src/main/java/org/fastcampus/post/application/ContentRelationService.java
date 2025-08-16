package org.fastcampus.post.application;

import org.fastcampus.post.application.interfaces.ContentRelationRepository;
import org.fastcampus.post.domain.common.ContentAction;
import org.fastcampus.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class ContentRelationService {
    private final ContentRelationRepository contentRelationRepository;
    public ContentRelationService(ContentRelationRepository contentRelationRepository) {
        this.contentRelationRepository = contentRelationRepository;
    }

    public void like(User user, ContentAction contentAction) {
        if (contentAction.isAuthor(user)) {
            throw new IllegalArgumentException();
        }
        if (contentRelationRepository.alreadyLiked(contentAction, user)) {
            throw new IllegalArgumentException();
        }
        contentAction.getLike(user);
        contentRelationRepository.like(contentAction, user);
    }

    public void dislike(User user, ContentAction contentAction) {
        if (!contentRelationRepository.alreadyLiked(contentAction, user)) {
            throw new IllegalArgumentException();
        }
        contentAction.loseLike();
        contentRelationRepository.dislike(contentAction, user);
    }

}
