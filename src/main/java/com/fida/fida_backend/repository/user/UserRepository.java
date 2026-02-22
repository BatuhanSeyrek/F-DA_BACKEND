package com.fida.fida_backend.repository.user;

import com.fida.fida_backend.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String username);

    Optional<Object> findByEmail(String email);
}
