package com.example.TodoListWithSecurity.security;

import com.example.TodoListWithSecurity.model.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.sql.DataSource;

import static com.example.TodoListWithSecurity.model.enums.UserAuthorities.WRITE;
import static com.example.TodoListWithSecurity.model.enums.UserRole.ADMIN;
import static com.example.TodoListWithSecurity.model.enums.UserRole.USER;

@Configuration
@EnableWebSecurity

public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {




    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    DataSource dataSource;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .authoritiesByUsernameQuery("select username, userRole FROM users where username=?")
                .usersByUsernameQuery("select username,password  FROM users where username=?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/update").hasAnyRole("USER","ADMIN")
                .and()
                .httpBasic();
    }




}
