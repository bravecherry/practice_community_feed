package org.fastcampus.post.repository;

import java.util.HashSet;
import java.util.Set;
import org.fastcampus.post.domain.common.ContentAction;
import org.fastcampus.post.application.interfaces.ContentRelationRepository;
import org.fastcampus.user.domain.User;

public class FakeContentRelationRepository implements ContentRelationRepository {

    Set<Relation> map = new HashSet<>();

    @Override
    public boolean alreadyLiked(ContentAction contentAction, User user) {
        return map.contains(new Relation(user, contentAction));
    }

    @Override
    public void like(ContentAction contentAction, User user) {
        map.add(new Relation(user, contentAction));
    }

    @Override
    public void dislike(ContentAction contentAction, User user) {
        map.remove(new Relation(user, contentAction));
    }

    record Relation(User user, ContentAction contentAction) {}
}
