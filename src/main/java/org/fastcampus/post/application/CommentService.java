package org.fastcampus.post.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.application.dto.CreateCommentReqDto;
import org.fastcampus.post.application.dto.LikeReqDto;
import org.fastcampus.post.application.dto.UpdateCommentReqDto;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ContentRelationService contentRelationService;
    private final PostService postService;

    public Comment getComment(Long id) {
        return commentRepository.findById(id);
    }

    public Comment create(CreateCommentReqDto reqDto) {
        User author = userService.getUser(reqDto.userId());
        Post post = postService.getPost(reqDto.postId());
        Comment comment = Comment.createComment(post, author, reqDto.content());
        return commentRepository.save(comment);
    }

    public void update(Long commentId, UpdateCommentReqDto reqDto) {
        User author = userService.getUser(reqDto.userId());
        Comment comment = getComment(commentId);
        if (!comment.isAuthor(author)) {
            throw new IllegalArgumentException();
        }
        comment.updateComment(author, reqDto.content());
        commentRepository.save(comment);
    }

    public List<Comment> getByPost(Post post) {
        return commentRepository.findByPost(post);
    }

    public void like(LikeReqDto reqDto) {
        User user = userService.getUser(reqDto.userId());
        Comment comment = getComment(reqDto.targetId());
        contentRelationService.like(user, comment);
        commentRepository.updateLikeCount(comment);
    }

    public void dislike(LikeReqDto reqDto) {
        User user = userService.getUser(reqDto.userId());
        Comment comment = getComment(reqDto.targetId());
        contentRelationService.dislike(user, comment);
        commentRepository.updateLikeCount(comment);
    }

}
