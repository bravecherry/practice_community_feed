package org.fastcampus.post.repository;

import java.util.ArrayList;
import java.util.List;
import org.fastcampus.post.application.interfaces.PostRelationRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.user.domain.User;

public class FakePostRelationRepository implements PostRelationRepository {

    List<Relation> relation = new ArrayList<>();

    @Override
    public boolean alreadyLiked(User user, Post post) {
        return relation.contains(new Relation(user.getId(), post.getId()));
    }

    @Override
    public void like(User user, Post post) {
        relation.add(new Relation(user.getId(), post.getId()));
    }

    @Override
    public void dislike(User user, Post post) {
        relation.remove(new Relation(user.getId(), post.getId()));
    }

    record Relation(Long userId, Long postId) {}
}
