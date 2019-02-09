package com.scarlatti.springsecuritybaseforreactjs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Alessandro Scarlatti
 * @since Saturday, 2/9/2019
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("asdf")
            .password("asdf")
            .roles("USER")
        .and()
            .withUser("guest")
            .password("guest")
            .roles("GUEST");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/error").permitAll()
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated();

        http
            .formLogin()
            .and()
            .logout()
            .permitAll();

        http.rememberMe();
        http.csrf().disable();
    }
}
