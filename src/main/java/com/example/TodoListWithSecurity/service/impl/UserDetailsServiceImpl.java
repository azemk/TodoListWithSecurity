package com.example.TodoListWithSecurity.service.impl;

import com.example.TodoListWithSecurity.model.Users;
import com.example.TodoListWithSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findUsersByUsername(username);
        if(users == null){
            throw new UsernameNotFoundException("User not found!");
        }
        return new UserDetailsImpl(users);
    }
}
