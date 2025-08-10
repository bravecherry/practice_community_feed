package org.fastcampus.post.application;

import java.util.List;
import org.fastcampus.post.application.dto.CreateCommentReqDto;
import org.fastcampus.post.application.dto.UpdateCommentReqDto;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.user.domain.User;

public class CommentService {
    private final CommentRepository commentRepository;
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Comment create(User author, Post post, CreateCommentReqDto reqDto) {
        Comment comment = new Comment(null, post, author, new CommentContent(reqDto.content()));
        return commentRepository.save(comment);
    }

    public void update(User author, Comment comment, UpdateCommentReqDto reqDto) {
        if (!comment.isAuthor(author)) {
            throw new IllegalArgumentException();
        }
        comment.updateComment(author, reqDto.content());
        commentRepository.save(comment);
    }

    public List<Comment> getByPost(Post post) {
        return commentRepository.findByPost(post);
    }

}
