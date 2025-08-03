package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.CreatePostReqDto;
import org.fastcampus.post.application.dto.UpdatePostReqDto;
import org.fastcampus.post.application.interfaces.PostRelationRepository;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;

public class PostService {
    private final UserService userService;
    private final PostRepository postRepository;
    private final PostRelationRepository postRelationRepository;
    public PostService(UserService userService,
            PostRepository postRepository,
            PostRelationRepository postRelationRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.postRelationRepository = postRelationRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Post create(Long userId, CreatePostReqDto reqDto) {
        User author = userService.getUser(userId);
        Post post = new Post(null, author, new PostContent(reqDto.content()), reqDto.visibleState());
        return postRepository.save(post);
    }

    public void update(Long userId, Long postId, UpdatePostReqDto reqDto) {
        User author = userService.getUser(userId);
        Post post = getPost(postId);
        if (!post.isAuthor(author)) {
            throw new IllegalArgumentException();
        }
        post.updatePost(author, reqDto.content(), reqDto.visibleState());
    }

    public void like(Long userId, Long postId) {
        User user = userService.getUser(userId);
        Post post = getPost(postId);
        if (post.isAuthor(user)) {
            throw new IllegalArgumentException();
        }
        if (postRelationRepository.alreadyLiked(user, post)) {
            throw new IllegalArgumentException();
        }
        post.getLike(user);
        postRelationRepository.like(user, post);
    }

    public void dislike(Long userId, Long postId) {
        User user = userService.getUser(userId);
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        if (!postRelationRepository.alreadyLiked(user, post)) {
            throw new IllegalArgumentException();
        }
        post.loseLike();
        postRelationRepository.dislike(user, post);
    }

}
