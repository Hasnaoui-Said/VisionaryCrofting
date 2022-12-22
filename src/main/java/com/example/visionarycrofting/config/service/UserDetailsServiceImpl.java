package com.example.visionarycrofting.config.service;

import com.example.visionarycrofting.entity.User;
import com.example.visionarycrofting.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser = userDao.findByUsername(username);
        if (appUser == null)
            throw new UsernameNotFoundException("Username not found!!");
        Collection<GrantedAuthority> authorities = appUser.getRoles().stream()
                .map(usersRoles -> new SimpleGrantedAuthority(usersRoles.getRole().getName()))
                .collect(Collectors.toList());
        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core
                .userdetails.User(appUser.getUsername(), appUser.getPassword(), authorities);
        return userDetails;
    }
}
