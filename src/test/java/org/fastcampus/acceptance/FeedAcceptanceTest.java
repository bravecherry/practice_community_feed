package org.fastcampus.acceptance;

import static org.fastcampus.acceptance.steps.FeedAcceptanceStep.reqCreatePost;
import static org.fastcampus.acceptance.steps.FeedAcceptanceStep.requestFeed;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.fastcampus.acceptance.utils.AcceptanceTestTemplate;
import org.fastcampus.post.application.dto.CreatePostReqDto;
import org.fastcampus.post.domain.content.PostVisibleState;
import org.fastcampus.post.ui.dto.GetPostContentResDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FeedAcceptanceTest extends AcceptanceTestTemplate {

    /**
     * user1 --- follow ---> user2
     * user1 --- follow ---> user3
     */
    @BeforeEach
    void setUp() {
        super.init();
    }

    /**
     * user2 posts a post
     * user1 gets user2's post on feed
     */
    @Test
    void givenUserHasFollower_whenFollowingUserPostsAPost_thenFeedUpdated() {
        //given
        CreatePostReqDto reqDto = new CreatePostReqDto("post post post post post ", 2L, PostVisibleState.PUBLIC);
        Long id = reqCreatePost(reqDto);

        //when
        List<GetPostContentResDto> list = requestFeed(1L);

        //then
        assertEquals(1, list.size());
        assertEquals(id, list.get(0).getId());
    }
}
