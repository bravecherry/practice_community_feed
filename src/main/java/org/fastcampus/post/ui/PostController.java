package org.fastcampus.post.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.common.ui.Response;
import org.fastcampus.post.application.PostService;
import org.fastcampus.post.application.dto.CreatePostReqDto;
import org.fastcampus.post.application.dto.LikeReqDto;
import org.fastcampus.post.application.dto.UpdatePostReqDto;
import org.fastcampus.post.domain.Post;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public Response<Long> createPost(@RequestBody CreatePostReqDto reqDto) {
        Post post = postService.create(reqDto);
        return Response.ok(post.getId());
    }

    @PatchMapping("/{postId}")
    public Response<Void> updatePost(@PathVariable Long postId, @RequestBody UpdatePostReqDto reqDto) {
        postService.update(postId, reqDto);
        return Response.ok(null);
    }

    @PostMapping("/like")
    public Response<Void> like(@RequestBody LikeReqDto reqDto) {
        postService.like(reqDto);
        return Response.ok(null);
    }

    @PostMapping("/dislike")
    public Response<Void> dislike(@RequestBody LikeReqDto reqDto) {
        postService.dislike(reqDto);
        return Response.ok(null);
    }

}
