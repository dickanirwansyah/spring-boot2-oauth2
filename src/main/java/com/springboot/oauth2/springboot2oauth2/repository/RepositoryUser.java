package com.springboot.oauth2.springboot2oauth2.repository;

import com.springboot.oauth2.springboot2oauth2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RepositoryUser extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username)")
    User findByUsernameInCaseSensitive(@Param("username")String username);

    @Query
    User findByUsername(String username);

    @Query
    User findByEmail(String email);

    @Query
    User findByIdusers(String idusers);
}
