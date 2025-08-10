package org.fastcampus.user.repository;

import java.util.HashSet;
import java.util.Set;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.domain.User;

public class FakeUserRelationRepository implements UserRelationRepository {

    private final Set<Relation> relations = new HashSet<>();

    @Override
    public boolean alreadyFollows(User user, User targetUser) {
        return relations.contains(new Relation(user.getId(), targetUser.getId()));
    }

    @Override
    public void save(User user, User targetUser) {
        relations.add(new Relation(user.getId(), targetUser.getId()));
    }

    @Override
    public void delete(User user, User targetUser) {
        relations.remove(new Relation(user.getId(), targetUser.getId()));
    }
}

record Relation(Long userId, Long targetUserId) {}
