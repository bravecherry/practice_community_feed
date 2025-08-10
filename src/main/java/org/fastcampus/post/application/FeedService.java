package org.fastcampus.post.application;

import java.util.List;
import org.fastcampus.post.application.interfaces.FeedRepository;
import org.fastcampus.post.domain.Feed.Feed;
import org.fastcampus.user.domain.User;

public class FeedService {

    private final FeedRepository feedRepository;

    public FeedService(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    public List<Feed> getFeedList(User user) {
        return feedRepository.getFeeds(user);
    }

}
