package org.fastcampus.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.fastcampus.post.application.dto.CreateCommentReqDto;
import org.fastcampus.post.application.dto.CreatePostReqDto;
import org.fastcampus.post.application.dto.UpdateCommentReqDto;
import org.fastcampus.post.application.dto.UpdatePostReqDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.domain.content.PostVisibleState;
import org.fastcampus.post.repository.FakeCommentRepository;
import org.fastcampus.post.repository.FakePostRepository;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserReqDto;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommentServiceTest {
    User author;
    Post post;
    Comment comment;
    private final FakeUserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final FakePostRepository postRepository = new FakePostRepository();
    private final PostService postService = new PostService(postRepository);
    private final FakeCommentRepository postCommentRepository = new FakeCommentRepository();
    private final CommentService commentService = new CommentService(postCommentRepository);

    @BeforeEach
    void setUp() {
        CreateUserReqDto reqDto = new CreateUserReqDto("author", "");
        author = userService.createUser(reqDto);
        PostVisibleState state = PostVisibleState.PUBLIC;
        String content = "aaaaaaa";
        CreatePostReqDto createPostReqDto = new CreatePostReqDto(content, state);
        post = postService.create(author, createPostReqDto);
        CreateCommentReqDto createCommentReqDto = new CreateCommentReqDto(content);
        comment = commentService.create(author, post, createCommentReqDto);
    }

    @Test
    void givenId_whenFindById_thenReturnComment() {
        assertEquals(comment, commentService.getComment(comment.getId()));
    }

    @Test
    void givenRequest_whenSave_thenReturnComment() {
        //given
        String content = "comment";
        CreateCommentReqDto reqDto = new CreateCommentReqDto(content);
        //when
        Comment newComment = commentService.create(author, post, reqDto);
        assertEquals(content, newComment.getContentMessage());
    }

    @Test
    void givenRequest_whenUpdate_thenSuccess() {
        //given
        String updateContent = "bbbbbbb";
        UpdateCommentReqDto reqDto = new UpdateCommentReqDto(updateContent);
        //when
        commentService.update(author, comment, reqDto);
        comment = commentService.getComment(comment.getId());
        //then
        assertEquals(updateContent, comment.getContentMessage());
    }

    @Test
    void givenPost_thenReturnList() {
        //given
        List<Comment> list = commentService.getByPost(post);
        //when
        assertEquals(1, list.size());
    }

}
