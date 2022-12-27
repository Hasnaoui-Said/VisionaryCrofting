package com.example.visionarycrofting.config.security;

import com.example.visionarycrofting.config.security.exception.AccessDeniedExceptionHandler;
import com.example.visionarycrofting.config.security.filter.JwtAuthenticationFilter;
import com.example.visionarycrofting.config.security.filter.JwtAutorizationFilter;
import com.example.visionarycrofting.config.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                .and()// Exception handler
                .exceptionHandling().accessDeniedHandler(new AccessDeniedExceptionHandler())
                // 2 solution
                // 1- use antMatchers
                // 2- use annotation in application EnableGlobalMethodSecurity and any method PostAuthorize(hasAuth(''))
                .and().authorizeRequests()
                .antMatchers("/**/client/**", "/**/command/**", "/**/product/**", "/**/offer/**", "/**/command-item/**", "/**/stock/**", "/**/supplier/**").hasAuthority("ADMIN")
                .antMatchers( "/**/product/**", "/**/offer/**").hasAuthority("MANAGER")
                .antMatchers("/**/stock/**", "/**/supplier/**").hasAuthority("MANAGER")
                .antMatchers("/**/stock/**", "/**/supplier/**").hasAnyAuthority()
                .antMatchers( "/**/offer/**").hasAuthority("SUPPLIER")
                .antMatchers("/**/command/**", "/**/product/**").hasAuthority("CLIENT")
                .antMatchers("/**/jeton/**").permitAll()
                // any request accused with authenticated
                .anyRequest().authenticated()
                // integrate filter to my app
                .and().addFilter(new JwtAuthenticationFilter(authenticationManagerBean()))
                // notion middleware: filter second->  communique with other app as a passerelle
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