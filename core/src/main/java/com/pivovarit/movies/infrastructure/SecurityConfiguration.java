package com.pivovarit.movies.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
        throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser(User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build())
            .and()
            .inMemoryAuthentication()
            .withUser(User.withDefaultPasswordEncoder().username("admin").password("admin").roles("USER, ADMIN")
                .build());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
    }
}