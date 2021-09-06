package com.example.TodoListWithSecurity.controller;

import com.example.TodoListWithSecurity.UserRequestDto;
import com.example.TodoListWithSecurity.model.Users;
import com.example.TodoListWithSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/register")
    private Users signup(@RequestBody UserRequestDto userRequestDto){
        return userService.create(userRequestDto);
    }
    @PostMapping("/update")
    private Users updateUser(@RequestBody Users users){
        return userService.update(users);
    }
}
