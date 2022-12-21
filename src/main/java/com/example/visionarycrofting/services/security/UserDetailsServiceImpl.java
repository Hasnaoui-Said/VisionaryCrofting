package com.example.visionarycrofting.services.security;

import com.example.visionarycrofting.entity.User;
import com.example.visionarycrofting.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public org.springframework.security.core.userdetails.User loadUserByUsername(String username) {
        User appUser = userDao.findByUsername(username);
        Collection<GrantedAuthority> authorities = appUser.getRoles().stream()
                .map(usersRoles -> new SimpleGrantedAuthority(usersRoles.getRole().getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core
                .userdetails.User(appUser.getUsername(), appUser.getPassword(), authorities);
    }
}
