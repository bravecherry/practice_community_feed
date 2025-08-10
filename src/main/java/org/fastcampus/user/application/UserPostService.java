package org.fastcampus.user.application;

import java.util.List;
import org.fastcampus.post.domain.Post;
import org.fastcampus.user.application.interfaces.UserPostRepository;
import org.fastcampus.user.domain.User;

public class UserPostService {
    private final UserPostRepository userPostRepository;

    public UserPostService(UserPostRepository userPostRepository) {
        this.userPostRepository = userPostRepository;
    }

    public List<User> getUsersWhoLikesPost(Post post) {
        return userPostRepository.getUsersWhoLikesPost(post);
    }

}
