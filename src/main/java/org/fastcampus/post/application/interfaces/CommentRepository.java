package org.fastcampus.post.application.interfaces;

import java.util.List;
import java.util.Optional;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.user.domain.User;

public interface CommentRepository {

    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
    List<Comment> findByPost(Post post);
    List<Comment> findByUser(User user);

}
