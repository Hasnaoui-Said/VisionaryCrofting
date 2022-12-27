package com.example.visionarycrofting.config.security.dao;

import com.example.visionarycrofting.config.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE u.email=:q or u.username=:q")
    User findByUsernameOrEmail(@Param("q") String q);
}
