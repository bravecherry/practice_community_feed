package org.fastcampus.post.application;

import java.util.List;
import org.fastcampus.post.application.dto.CreateCommentReqDto;
import org.fastcampus.post.application.dto.UpdateCommentReqDto;
import org.fastcampus.post.application.interfaces.PostCommentRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;

public class PostCommentService {
    private final UserService userService;
    private final PostService postService;
    private final PostCommentRepository postCommentRepository;
    public PostCommentService(UserService userService, PostService postService, PostCommentRepository postCommentRepository) {
        this.userService = userService;
        this.postService = postService;
        this.postCommentRepository = postCommentRepository;
    }

    public Comment getComment(Long id) {
        return postCommentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Comment create(Long userId, Long postId, CreateCommentReqDto reqDto) {
        User author = userService.getUser(userId);
        Post post = postService.getPost(postId);
        Comment comment = new Comment(null, post, author, new CommentContent(reqDto.content()));
        return postCommentRepository.save(comment);
    }

    public void update(Long userId, Long postId, UpdateCommentReqDto reqDto) {
        User author = userService.getUser(userId);
        Comment comment = getComment(postId);
        if (!comment.isAuthor(author)) {
            throw new IllegalArgumentException();
        }
        comment.updateComment(author, reqDto.content());
        postCommentRepository.save(comment);
    }

    public List<Comment> getByPostId(Long postId) {
        Post post = postService.getPost(postId);
        return postCommentRepository.findByPost(post);
    }

    public void like(Long userId, Long commentId) {
        User user = userService.getUser(userId);
        Comment comment = getComment(commentId);
        if (!comment.isAuthor(user)) {
            throw new IllegalArgumentException();
        }
        comment.like(user);
        postCommentRepository.save(comment);
    }

    public void dislike(Long commentId) {
        Comment comment = getComment(commentId);
        comment.dislike();
        postCommentRepository.save(comment);
    }

}
