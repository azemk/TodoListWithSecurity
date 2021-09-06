package com.example.TodoListWithSecurity.service;

import com.example.TodoListWithSecurity.UserRequestDto;
import com.example.TodoListWithSecurity.model.Users;

import java.util.List;

public interface UserService {

    Users create (UserRequestDto userRequestDto);
    Users update(Users users);
    List<Users> findAll();
    Users deleteByUsername(String username);
}
