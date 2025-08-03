package org.fastcampus.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.fastcampus.post.application.dto.CreatePostReqDto;
import org.fastcampus.post.application.dto.UpdatePostReqDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostVisibleState;
import org.fastcampus.post.repository.FakePostRelationRepository;
import org.fastcampus.post.repository.FakePostRepository;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserReqDto;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PostServiceTest {
    User author;
    private final FakeUserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final FakePostRepository postRepository = new FakePostRepository();
    private final FakePostRelationRepository postRelationRepository = new FakePostRelationRepository();
    private final PostService postService = new PostService(userService, postRepository, postRelationRepository);

    @BeforeEach
    void setUp() {
        CreateUserReqDto reqDto = new CreateUserReqDto("user1", "");
        author = userService.createUser(reqDto);
    }

    @Test
    void givenId_whenFindById_thenReturnPost() {
        //given
        CreatePostReqDto createPostReqDto = new CreatePostReqDto("aaaaaaa", PostVisibleState.PUBLIC );
        Post post = postService.create(author.getId(), createPostReqDto);
        //then
        assertEquals(post, postService.getPost(post.getId()));
    }

    @Test
    void givenRequest_whenSave_thenReturnPost() {
        //given
        PostVisibleState state = PostVisibleState.PUBLIC;
        String content = "aaaaaaa";
        CreatePostReqDto createPostReqDto = new CreatePostReqDto(content, state);
        //when
        Post post = postService.create(author.getId(), createPostReqDto);
        //then
        assertNotNull(post.getId());
        assertEquals(content, post.getContentMessage());
        assertEquals(state, post.getVisibleState());
    }

    @Test
    void givenRequest_whenUpdate_thenSuccess() {
        //given
        PostVisibleState state = PostVisibleState.PUBLIC;
        String content = "aaaaaaa";
        CreatePostReqDto createPostReqDto = new CreatePostReqDto(content, state);
        Post post = postService.create(author.getId(), createPostReqDto);
        String updateContent = "bbbbbbb";
        PostVisibleState updateState = PostVisibleState.FOLLOWER_ONLY;
        UpdatePostReqDto reqDto = new UpdatePostReqDto(updateContent, updateState);
        //when
        postService.update(author.getId(), post.getId(), reqDto);
        post = postService.getPost(post.getId());
        //then
        assertEquals(updateContent, post.getContentMessage());
        assertEquals(updateState, post.getVisibleState());
    }


}
