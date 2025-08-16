package org.fastcampus.post.repository;

import java.util.HashMap;
import java.util.Map;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostContent;

public class FakePostRepository implements PostRepository {

    Map<Long, Post> posts = new HashMap<>();

    @Override
    public Post save(Post post) {
        if (posts.containsKey(post.getId())) {
            posts.put(post.getId(), post);
            return post;
        }
        long id = posts.size() + 1;
        Post newPost = new Post(id, post.getAuthor(), new PostContent(post.getContentMessage()), post.getVisibleState());
        posts.put(id, newPost);
        return newPost;
    }

    @Override
    public Post findById(Long id) {
        return posts.get(id);
    }

}
