package org.fastcampus.post.application.interfaces;

import java.util.List;
import java.util.Optional;
import org.fastcampus.post.application.dto.db.PostWithCommentCount;
import org.fastcampus.post.domain.Post;
import org.fastcampus.user.domain.User;

public interface PostRepository {

    Post save(Post post);
    Optional<Post> findById(Long id);
    boolean alreadyLiked(User user, Post post);
    void like(User user, Post post);
    void dislike(User user, Post post);

    List<PostWithCommentCount> getPostsByFollowersOrderByRegDtmDesc(User user);
}
