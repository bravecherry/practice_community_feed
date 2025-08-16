package org.fastcampus.fake;

import lombok.Getter;
import org.fastcampus.post.application.CommentService;
import org.fastcampus.post.application.ContentRelationService;
import org.fastcampus.post.application.PostService;
import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.application.interfaces.ContentRelationRepository;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.repository.FakeCommentRepository;
import org.fastcampus.post.repository.FakeContentRelationRepository;
import org.fastcampus.post.repository.FakePostRepository;
import org.fastcampus.user.application.UserRelationService;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.repository.FakeUserRelationRepository;
import org.fastcampus.user.repository.FakeUserRepository;

class FakeObjectFactory {

    @Getter
    private final UserRepository userRepository = new FakeUserRepository();
    @Getter
    private final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    @Getter
    private final PostRepository postRepository = new FakePostRepository();
    @Getter
    private final CommentRepository commentRepository = new FakeCommentRepository();

    @Getter
    private final UserService userService = new UserService(userRepository);
    @Getter
    private final UserRelationService userRelationService = new UserRelationService(userRelationRepository, userService);
    private final ContentRelationRepository contentRelationRepository = new FakeContentRelationRepository();
    private final ContentRelationService contentRelationService = new ContentRelationService(contentRelationRepository);
    @Getter
    private final PostService postService = new PostService(postRepository, userService, contentRelationService);
    @Getter
    private final CommentService commentService = new CommentService(commentRepository, userService, contentRelationService, postService);

    private FakeObjectFactory() {

    }

}
