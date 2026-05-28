package com.github.nomis778.puddle.server.user;

import com.github.nomis778.puddle.server.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
}