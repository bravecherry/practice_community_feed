package org.fastcampus.user.application.interfaces;

import java.util.List;
import org.fastcampus.post.domain.Post;
import org.fastcampus.user.domain.User;

public interface UserPostRepository {

    List<User> getUsersWhoLikesPost(Post post);

}
