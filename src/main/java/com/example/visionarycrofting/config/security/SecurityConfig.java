package com.example.visionarycrofting.config.security;

import com.example.visionarycrofting.config.security.exception.AccessDeniedExceptionHandler;
import com.example.visionarycrofting.config.security.filter.JwtAuthenticationFilter;
import com.example.visionarycrofting.config.security.filter.JwtAutorizationFilter;
import com.example.visionarycrofting.config.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().frameOptions().disable()
                .and()// Exception handler
                .exceptionHandling().accessDeniedHandler(new AccessDeniedExceptionHandler())
                // 2 solution
                // 1- use antMatchers
                // 2- use annotation in application EnableGlobalMethodSecurity and any method PostAuthorize(hasAuth(''))
                .and().authorizeRequests().antMatchers(HttpMethod.GET,"/**/supplier/**").hasAuthority("SUPPLIER")
                .and().authorizeRequests().antMatchers("/**/jeton/**").permitAll()
                .and().authorizeRequests().anyRequest().authenticated()
                .and().addFilter(new JwtAuthenticationFilter(authenticationManagerBean()))
                // notion middleware: filter second
                .addFilterBefore(new JwtAutorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}