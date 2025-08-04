package org.fastcampus.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.fastcampus.post.repository.FakeFeedRepository;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserReqDto;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeedServiceTest {
    User user;
    private final FakeUserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final FakeFeedRepository feedRepository = new FakeFeedRepository();
    private final FeedService feedService = new FeedService(feedRepository);

    @BeforeEach
    void setUp() {
        CreateUserReqDto reqDto = new CreateUserReqDto("author", "");
        user = userService.createUser(reqDto);
    }

    @Test
    void givenRequest_thenReturnListOfFeeds() {
        assertEquals(List.of(), feedService.getFeedList(user));
    }

}
