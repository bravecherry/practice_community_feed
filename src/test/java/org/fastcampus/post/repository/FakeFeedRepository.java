package org.fastcampus.post.repository;

import java.util.ArrayList;
import java.util.List;
import org.fastcampus.post.application.interfaces.FeedRepository;
import org.fastcampus.post.domain.Feed.Feed;
import org.fastcampus.user.domain.User;

public class FakeFeedRepository implements FeedRepository {

    List<Feed> feeds = new ArrayList<>();

    @Override
    public List<Feed> getFeeds(User user) {
        return feeds;
    }
}
