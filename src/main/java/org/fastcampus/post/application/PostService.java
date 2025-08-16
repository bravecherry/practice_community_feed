package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.CreatePostReqDto;
import org.fastcampus.post.application.dto.LikeReqDto;
import org.fastcampus.post.application.dto.UpdatePostReqDto;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final ContentRelationService contentRelationService;

    public PostService(PostRepository postRepository, UserService userService,
            ContentRelationService contentRelationService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.contentRelationService = contentRelationService;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id);
    }

    public Post create(CreatePostReqDto reqDto) {
        User author = userService.getUser(reqDto.userId());
        Post post = Post.createPost(author, reqDto.content(), reqDto.visibleState());
        return postRepository.save(post);
    }

    public void update(Long postId, UpdatePostReqDto reqDto) {
        Post post = getPost(postId);
        User user = userService.getUser(reqDto.userId());
        if (!post.isAuthor(user)) {
            throw new IllegalArgumentException();
        }
        post.updatePost(reqDto.content(), reqDto.visibleState());
        postRepository.save(post);
    }

    public void like(LikeReqDto reqDto) {
        User user = userService.getUser(reqDto.userId());
        Post post = getPost(reqDto.targetId());
        contentRelationService.like(user, post);
        postRepository.updateLikeCount(post);
    }

    public void dislike(LikeReqDto reqDto) {
        User user = userService.getUser(reqDto.userId());
        Post post = getPost(reqDto.targetId());
        contentRelationService.dislike(user, post);
        postRepository.updateLikeCount(post);
    }

}
