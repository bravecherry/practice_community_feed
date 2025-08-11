package org.fastcampus.fake;

import org.fastcampus.post.application.CommentService;
import org.fastcampus.post.application.PostService;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.repository.FakeCommentRepository;
import org.fastcampus.post.repository.FakePostRepository;
import org.fastcampus.user.application.UserRelationService;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.repository.FakeUserRelationRepository;
import org.fastcampus.user.repository.FakeUserRepository;

class FakeObjectFactory {

    private final UserRepository userRepository = new FakeUserRepository();
    private final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private final PostRepository postRepository = new FakePostRepository();
    private final CommentRepository commentRepository = new FakeCommentRepository();

    private final UserService userService = new UserService(userRepository);
    private final UserRelationService userRelationService = new UserRelationService(userRelationRepository, userService);
    private final PostService postService = new PostService(postRepository);
    private final CommentService commentService = new CommentService(commentRepository);

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public UserRelationRepository getUserRelationRepository() {
        return userRelationRepository;
    }

    public PostRepository getPostRepository() {
        return postRepository;
    }

    public CommentRepository getCommentRepository() {
        return commentRepository;
    }

    public UserService getUserService() {
        return userService;
    }

    public UserRelationService getUserRelationService() {
        return userRelationService;
    }

    public PostService getPostService() {
        return postService;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    private FakeObjectFactory() {

    }

}
