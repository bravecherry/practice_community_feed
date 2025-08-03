package org.fastcampus.post.application;

import java.util.List;
import org.fastcampus.post.application.interfaces.FeedRepository;
import org.fastcampus.post.domain.Feed.Feed;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;

public class FeedService {

    private final UserService userService;
    private final FeedRepository feedRepository;

    public FeedService(UserService userService, FeedRepository feedRepository) {
        this.userService = userService;
        this.feedRepository = feedRepository;
    }

    public List<Feed> getFeedList(Long userId) {
        User user = userService.getUser(userId);
        return feedRepository.getFeeds(user);
    }

}
