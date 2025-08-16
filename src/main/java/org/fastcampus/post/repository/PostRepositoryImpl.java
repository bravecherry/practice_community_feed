package org.fastcampus.post.repository;

import lombok.RequiredArgsConstructor;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;

    @Override
    public Post save(Post post) {
        PostEntity postEntity = new PostEntity(post);
        if (post.getId() != null) {
            jpaPostRepository.updatePost(postEntity);
            return postEntity.toPost();
        }
        postEntity = jpaPostRepository.save(new PostEntity(post));
        return postEntity.toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity postEntity = jpaPostRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return postEntity.toPost();
    }

}
