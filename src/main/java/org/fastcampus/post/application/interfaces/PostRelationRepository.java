package org.fastcampus.post.application.interfaces;

import org.fastcampus.post.domain.Post;
import org.fastcampus.user.domain.User;

public interface PostRelationRepository {

    boolean alreadyLiked(User user, Post post);
    void like(User user, Post post);
    void dislike(User user, Post post);
}
