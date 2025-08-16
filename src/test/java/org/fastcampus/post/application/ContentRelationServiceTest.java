package org.fastcampus.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.post.application.dto.CreateCommentReqDto;
import org.fastcampus.post.application.dto.CreatePostReqDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.domain.content.PostVisibleState;
import org.fastcampus.post.repository.FakeContentRelationRepository;
import org.fastcampus.post.repository.FakeCommentRepository;
import org.fastcampus.post.repository.FakePostRepository;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserReqDto;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContentRelationServiceTest {
    User author;
    Post post;
    Comment comment;
    private final FakeUserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final FakePostRepository postRepository = new FakePostRepository();
    private final FakeContentRelationRepository contentRelationRepository = new FakeContentRelationRepository();
    private final ContentRelationService contentRelationService = new ContentRelationService(contentRelationRepository);
    private final PostService postService = new PostService(postRepository, userService, contentRelationService);
    private final FakeCommentRepository postCommentRepository = new FakeCommentRepository();
    private final CommentService commentService = new CommentService(
            postCommentRepository);

    @BeforeEach
    void setUp() {
        CreateUserReqDto reqDto = new CreateUserReqDto("author", "");
        author = userService.createUser(reqDto);
        PostVisibleState state = PostVisibleState.PUBLIC;
        String content = "aaaaaaa";
        CreatePostReqDto createPostReqDto = new CreatePostReqDto(content, author.getId(), state);
        post = postService.create(createPostReqDto);
        CreateCommentReqDto commentReqDto = new CreateCommentReqDto("comment1");
        comment = commentService.create(author, post, commentReqDto);
    }

    @Test
    void givenUserAndPost_whenLike_thenUpdated() {
        //given
        CreateUserReqDto reqDto = new CreateUserReqDto("user", "");
        User user = userService.createUser(reqDto);
        //when
        contentRelationService.like(user, post);
        //then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenAuthorAndPost_whenLike_thenThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> contentRelationService.like(author, post));
    }

    @Test
    void givenAlreadyLikedUserAndPost_whenLike_thenThrowsException() {
        //given
        CreateUserReqDto reqDto = new CreateUserReqDto("user", "");
        User user = userService.createUser(reqDto);
        contentRelationService.like(user, post);
        //when
        assertThrows(IllegalArgumentException.class, () -> contentRelationService.like(user, post));
    }

    @Test
    void givenUserAndPost_whenDislike_thenUpdated() {
        //given
        CreateUserReqDto reqDto = new CreateUserReqDto("user", "");
        User user = userService.createUser(reqDto);
        //when
        contentRelationService.like(user, post);
        contentRelationService.dislike(user, post);
        //then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenNotLikedUserAndPost_whenDislike_thenThrowsException() {
        //given
        CreateUserReqDto reqDto = new CreateUserReqDto("user", "");
        User user = userService.createUser(reqDto);
        //then
        assertThrows(IllegalArgumentException.class, () -> contentRelationService.dislike(user, post));
    }

    @Test
    void givenUserAndComment_whenLike_thenUpdated() {
        //given
        CreateUserReqDto reqDto = new CreateUserReqDto("user", "");
        User user = userService.createUser(reqDto);
        //when
        contentRelationService.like(user, post);
        //then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenAuthorAndComment_whenLike_thenThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> contentRelationService.like(author, comment));
    }

    @Test
    void givenAlreadyLikedUserAndComment_whenLike_thenThrowsException() {
        //given
        CreateUserReqDto reqDto = new CreateUserReqDto("user", "");
        User user = userService.createUser(reqDto);
        contentRelationService.like(user, comment);
        //when
        assertThrows(IllegalArgumentException.class, () -> contentRelationService.like(user, comment));
    }

    @Test
    void givenUserAndComment_whenDislike_thenUpdated() {
        //given
        CreateUserReqDto reqDto = new CreateUserReqDto("user", "");
        User user = userService.createUser(reqDto);
        //when
        contentRelationService.like(user, comment);
        contentRelationService.dislike(user, comment);
        //then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenNotLikedUserAndComment_whenDislike_thenThrowsException() {
        //given
        CreateUserReqDto reqDto = new CreateUserReqDto("user", "");
        User user = userService.createUser(reqDto);
        //then
        assertThrows(IllegalArgumentException.class, () -> contentRelationService.dislike(user, comment));
    }

}
