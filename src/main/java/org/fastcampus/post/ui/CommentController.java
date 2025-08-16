package org.fastcampus.post.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.common.ui.Response;
import org.fastcampus.post.application.CommentService;
import org.fastcampus.post.application.dto.CreateCommentReqDto;
import org.fastcampus.post.application.dto.LikeReqDto;
import org.fastcampus.post.application.dto.UpdateCommentReqDto;
import org.fastcampus.post.domain.comment.Comment;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Response<Long> create(@RequestBody CreateCommentReqDto reqDto) {
        Comment comment = commentService.create(reqDto);
        return Response.ok(comment.getId());
    }

    @PatchMapping("/{commentId}")
    public Response<Void> update(@PathVariable Long commentId, @RequestBody UpdateCommentReqDto reqDto) {
        commentService.update(commentId, reqDto);
        return Response.ok(null);
    }

    @PostMapping("/like")
    public Response<Void> like(@PathVariable LikeReqDto reqDto) {
        commentService.like(reqDto);
        return Response.ok(null);
    }

    @PostMapping("/dislike")
    public Response<Void> dislike(@PathVariable LikeReqDto reqDto) {
        commentService.dislike(reqDto);
        return Response.ok(null);
    }

}
