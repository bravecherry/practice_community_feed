package org.fastcampus.post.repository;

import java.util.HashSet;
import java.util.Set;
import org.fastcampus.post.domain.common.ContentAction;
import org.fastcampus.post.application.interfaces.ContentRelationRepository;
import org.fastcampus.user.domain.User;

public class FakeContentRelationRepository implements ContentRelationRepository {

    Set<Relation> map = new HashSet<>();

    @Override
    public boolean alreadyLiked(User user, ContentAction contentAction) {
        return map.contains(new Relation(user, contentAction));
    }

    @Override
    public void like(User user, ContentAction contentAction) {
        map.add(new Relation(user, contentAction));
    }

    @Override
    public void dislike(User user, ContentAction contentAction) {
        map.remove(new Relation(user, contentAction));
    }

    record Relation(User user, ContentAction contentAction) {}
}
