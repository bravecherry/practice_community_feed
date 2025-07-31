package org.fastcampus.post.application;

import java.util.List;
import org.fastcampus.post.application.dto.CreatePostReqDto;
import org.fastcampus.post.application.dto.ReadFeedListResDto;
import org.fastcampus.post.application.dto.UpdatePostReqDto;
import org.fastcampus.post.application.dto.db.PostWithCommentCount;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;

public class PostService {
    private final UserService userService;
    private final PostRepository postRepository;
    public PostService(UserService userService, PostRepository postRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
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
        if (!postRepository.alreadyLiked(user, post)) {
            post.like(user);
            postRepository.like(user, post);
        }
    }

    public void dislike(Long userId, Long postId) {
        User user = userService.getUser(userId);
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        if (postRepository.alreadyLiked(user, post)) {
            post.dislike();
            postRepository.dislike(user, post);
        }
    }

    public ReadFeedListResDto getFeedList(Long userId) {
        User user = userService.getUser(userId);
        List<PostWithCommentCount> list = postRepository.getPostsByFollowersOrderByRegDtmDesc(user);
        return new ReadFeedListResDto(list, user);
    }

}
