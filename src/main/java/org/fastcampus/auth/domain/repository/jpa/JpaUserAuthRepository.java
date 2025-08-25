package org.fastcampus.auth.domain.repository.jpa;

import org.fastcampus.auth.domain.repository.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserAuthRepository extends JpaRepository<UserAuthEntity, String> {

}
