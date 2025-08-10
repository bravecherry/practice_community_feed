package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.CreatePostReqDto;
import org.fastcampus.post.application.dto.UpdatePostReqDto;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.user.domain.User;

public class PostService {
    private final PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Post create(User author, CreatePostReqDto reqDto) {
        Post post = Post.createPost(author, reqDto.content(), reqDto.visibleState());
        return postRepository.save(post);
    }

    public void update(User author, Post post, UpdatePostReqDto reqDto) {
        if (!post.isAuthor(author)) {
            throw new IllegalArgumentException();
        }
        post.updatePost(author, reqDto.content(), reqDto.visibleState());
    }

}
