package org.fastcampus.post.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.user.domain.User;

public class FakeCommentRepository implements CommentRepository {

    Map<Long, Comment> comments = new HashMap<>();

    @Override
    public Comment save(Comment comment) {
        if (comments.containsKey(comment.getId())) {
            comments.put(comment.getId(), comment);
            return comment;
        }
        long id = comments.size() + 1;
        Comment newComment = new Comment(id, comment.getPost(), comment.getAuthor(), new CommentContent(comment.getContentMessage()));
        comments.put(id, newComment);
        return newComment;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(comments.get(id));
    }

    @Override
    public List<Comment> findByPost(Post post) {
        return comments.values().stream().filter(it -> it.getPost().equals(post)).toList();
    }

    @Override
    public List<Comment> findByUser(User user) {
        return comments.values().stream().filter(it -> it.getAuthor().equals(user)).toList();
    }

}
