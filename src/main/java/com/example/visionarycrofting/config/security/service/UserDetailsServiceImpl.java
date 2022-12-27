package com.example.visionarycrofting.config.security.service;

import com.example.visionarycrofting.config.security.entity.User;
import com.example.visionarycrofting.config.security.dao.UserDao;
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
    @Autowired
    private UserDao userDao;

    public User findByUsername(String username) {
        return userDao.findByUsernameOrEmail(username);
    }

    // fetch user by username or email and list of authorities , check password before apply to successfulAuthentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Look up the user in the database by username or email
        User appUser = this.findByUsername(username);
        // If the user is not found, throw an exception
        if (appUser == null) {
            System.out.println(String.format("User with username %s was not found", username));
            throw new UsernameNotFoundException(String.format("User with username %s was not found", username));
        }
        Collection<GrantedAuthority> authorities = appUser.getRoles().stream()
                .map(usersRoles -> new SimpleGrantedAuthority(usersRoles.getRole().getName()))
                .collect(Collectors.toList());
        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), authorities);
        return userDetails;
    }
}
