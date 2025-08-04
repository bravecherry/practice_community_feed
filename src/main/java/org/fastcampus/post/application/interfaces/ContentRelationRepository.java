package org.fastcampus.post.application.interfaces;

import org.fastcampus.post.domain.common.ContentAction;
import org.fastcampus.user.domain.User;

public interface ContentRelationRepository {

    boolean alreadyLiked(User user, ContentAction contentAction);
    void like(User user, ContentAction contentAction);
    void dislike(User user, ContentAction contentAction);

}
