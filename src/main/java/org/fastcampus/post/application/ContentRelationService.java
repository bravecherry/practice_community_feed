package org.fastcampus.post.application;

import org.fastcampus.post.application.interfaces.ContentRelationRepository;
import org.fastcampus.post.domain.common.ContentAction;
import org.fastcampus.user.domain.User;

public class ContentRelationService {
    private final ContentRelationRepository contentRelationRepository;
    public ContentRelationService(ContentRelationRepository contentRelationRepository) {
        this.contentRelationRepository = contentRelationRepository;
    }

    public void like(User user, ContentAction contentAction) {
        if (contentAction.isAuthor(user)) {
            throw new IllegalArgumentException();
        }
        if (contentRelationRepository.alreadyLiked(user, contentAction)) {
            throw new IllegalArgumentException();
        }
        contentAction.getLike(user);
        contentRelationRepository.like(user, contentAction);
    }

    public void dislike(User user, ContentAction contentAction) {
        if (!contentRelationRepository.alreadyLiked(user, contentAction)) {
            throw new IllegalArgumentException();
        }
        contentAction.loseLike();
        contentRelationRepository.dislike(user, contentAction);
    }

}
