package com.example.visionarycrofting.config.security.dao;

import com.example.visionarycrofting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, String> {

    @Query("SELECT p FROM User p WHERE p.email=:q or p.username=:q")
    User findByUsernameOrEmail(@Param("q") String q);
}
