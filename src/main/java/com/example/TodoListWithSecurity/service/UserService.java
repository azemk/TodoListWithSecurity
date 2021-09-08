package com.example.TodoListWithSecurity.service;

import com.example.TodoListWithSecurity.dto.UserRequestDto;
import com.example.TodoListWithSecurity.dto.UserResponseDto;
import com.example.TodoListWithSecurity.model.Users;

import java.util.List;

public interface UserService {

    UserResponseDto create (UserRequestDto userRequestDto);
    Users update(Users users);
    List<Users> findAll();
    Users deleteByUsername(String username);
}
