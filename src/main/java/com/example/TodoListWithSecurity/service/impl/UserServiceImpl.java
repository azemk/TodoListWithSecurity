package com.example.TodoListWithSecurity.service.impl;

import com.example.TodoListWithSecurity.UserRequestDto;
import com.example.TodoListWithSecurity.model.Users;
import com.example.TodoListWithSecurity.model.enums.UserRole;
import com.example.TodoListWithSecurity.repository.UserRepository;
import com.example.TodoListWithSecurity.security.PasswordConfig;
import com.example.TodoListWithSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository usersRepository;

    @Override
    public Users create(UserRequestDto userRequestDto) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Users users = new Users();
        users.setUsername(userRequestDto.getUsername());
        users.setPassword(bCryptPasswordEncoder.encode(userRequestDto.getPassword()));
        users.setUserRole(UserRole.USER);
        users.setIsActive(true);
        usersRepository.save(users);
        return users;
    }

    @Override
    public Users update(Users users) {
       return usersRepository.save(users);
    }

    @Override
    public List<Users> findAll() {
        return null;
    }

    @Override
    public Users deleteByUsername(String username) {
        return null;
    }
}
