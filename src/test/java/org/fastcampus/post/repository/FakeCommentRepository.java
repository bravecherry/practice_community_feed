package org.fastcampus.post.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.domain.content.CommentContent;

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
    public Comment findById(Long id) {
        return comments.get(id);
    }

    @Override
    public List<Comment> findByPost(Post post) {
        return comments.values().stream().filter(it -> it.getPost().equals(post)).toList();
    }

}
