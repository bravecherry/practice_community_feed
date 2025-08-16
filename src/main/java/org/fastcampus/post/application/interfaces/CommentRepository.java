package org.fastcampus.post.application.interfaces;

import java.util.List;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;

public interface CommentRepository {

    Comment save(Comment comment);
    Comment findById(Long id);
    List<Comment> findByPost(Post post);

    void updateLikeCount(Comment comment);
}
