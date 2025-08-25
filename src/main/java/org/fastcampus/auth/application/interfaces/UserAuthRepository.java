package org.fastcampus.auth.application.interfaces;

import org.fastcampus.auth.domain.UserAuth;
import org.fastcampus.user.domain.User;

public interface UserAuthRepository {

    UserAuth register(UserAuth userAuth, User user);
}
