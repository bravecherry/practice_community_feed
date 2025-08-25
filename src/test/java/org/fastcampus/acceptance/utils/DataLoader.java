package org.fastcampus.acceptance.utils;

import static org.fastcampus.acceptance.steps.UserAcceptanceSteps.createUser;
import static org.fastcampus.acceptance.steps.UserAcceptanceSteps.followUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.fastcampus.user.application.dto.CreateUserReqDto;
import org.fastcampus.user.application.dto.FollowUserReqDto;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @PersistenceContext
    private EntityManager entityManager;

    public void loadData() {
        CreateUserReqDto reqDto = new CreateUserReqDto("testUser1", "");

        // id가 전부 다를 것이다.
        createUser(reqDto);
        createUser(reqDto);
        createUser(reqDto);

        followUser(new FollowUserReqDto(1L, 2L));
        followUser(new FollowUserReqDto(1L, 3L));
    }

    public String getToken(String email) {
        return entityManager.createNativeQuery("select token from community_email_verification where email = ?", String.class)
                .setParameter(1, email)
                .getSingleResult()
                .toString();
    }
}
