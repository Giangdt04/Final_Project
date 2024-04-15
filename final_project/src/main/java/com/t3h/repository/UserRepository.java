package com.t3h.repository;

import com.t3h.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
        UserEntity findUserByUsername(String username);
}
