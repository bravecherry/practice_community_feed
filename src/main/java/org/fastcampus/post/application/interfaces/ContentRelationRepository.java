package org.fastcampus.post.application.interfaces;

import org.fastcampus.post.domain.common.ContentAction;
import org.fastcampus.user.domain.User;

public interface ContentRelationRepository {

    boolean alreadyLiked(ContentAction contentAction, User user);
    void like(ContentAction contentAction, User user);
    void dislike(ContentAction contentAction, User user);

}
