package com.example.TodoListWithSecurity.service;

import com.example.TodoListWithSecurity.dto.UserRequestDto;
import com.example.TodoListWithSecurity.dto.UserResponseDto;
import com.example.TodoListWithSecurity.model.Users;

import java.util.List;


public interface UserService {
    UserResponseDto create (UserRequestDto userRequestDto);

    List<Users> findAll();
    void deleteByUsername(String username);
}
