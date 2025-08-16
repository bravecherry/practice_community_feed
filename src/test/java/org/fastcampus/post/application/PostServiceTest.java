package org.fastcampus.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.fastcampus.post.application.dto.CreatePostReqDto;
import org.fastcampus.post.application.dto.UpdatePostReqDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostVisibleState;
import org.fastcampus.post.repository.FakeContentRelationRepository;
import org.fastcampus.post.repository.FakePostRepository;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserReqDto;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PostServiceTest {
    User author;
    Post post;
    private final FakeUserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final FakePostRepository postRepository = new FakePostRepository();
    private final FakeContentRelationRepository contentRelationRepository = new FakeContentRelationRepository();
    private final ContentRelationService contentRelationService = new ContentRelationService(contentRelationRepository);
    private final PostService postService = new PostService(postRepository, userService, contentRelationService);

    @BeforeEach
    void setUp() {
        CreateUserReqDto reqDto = new CreateUserReqDto("author", "");
        author = userService.createUser(reqDto);
        PostVisibleState state = PostVisibleState.PUBLIC;
        String content = "aaaaaaa";
        CreatePostReqDto createPostReqDto = new CreatePostReqDto(content, author.getId(), state);
        post = postService.create(createPostReqDto);
    }

    @Test
    void givenId_whenFindById_thenReturnPost() {
        //given
        CreatePostReqDto createPostReqDto = new CreatePostReqDto("aaaaaaa", author.getId(), PostVisibleState.PUBLIC );
        Post newPost = postService.create(createPostReqDto);
        //then
        assertEquals(newPost, postService.getPost(newPost.getId()));
    }

    @Test
    void givenRequest_whenSave_thenReturnPost() {
        //given
        PostVisibleState state = PostVisibleState.PUBLIC;
        String content = "aaaaaaa";
        CreatePostReqDto createPostReqDto = new CreatePostReqDto(content, author.getId(), state);
        //when
        Post post = postService.create(createPostReqDto);
        //then
        assertNotNull(post.getId());
        assertEquals(content, post.getContentMessage());
        assertEquals(state, post.getVisibleState());
    }

    @Test
    void givenRequest_whenUpdate_thenSuccess() {
        //given

        String updateContent = "bbbbbbb";
        PostVisibleState updateState = PostVisibleState.FOLLOWER_ONLY;
        UpdatePostReqDto reqDto = new UpdatePostReqDto(updateContent, author.getId(), updateState);
        //when
        postService.update(post.getId(), reqDto);
        post = postService.getPost(post.getId());
        //then
        assertEquals(updateContent, post.getContentMessage());
        assertEquals(updateState, post.getVisibleState());
    }

}
