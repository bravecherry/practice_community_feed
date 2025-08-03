package org.fastcampus.post.application.interfaces;

import java.util.List;
import org.fastcampus.post.domain.Feed.Feed;
import org.fastcampus.user.domain.User;

public interface FeedRepository {

    List<Feed> getFeeds(User user);

}
