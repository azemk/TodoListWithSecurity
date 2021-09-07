package com.example.TodoListWithSecurity.controller;

import com.example.TodoListWithSecurity.dto.UserRequestDto;
import com.example.TodoListWithSecurity.dto.UserResponseDto;
import com.example.TodoListWithSecurity.model.Users;
import com.example.TodoListWithSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/register")
    private UserResponseDto signup(@RequestBody UserRequestDto userRequestDto){
        return userService.create(userRequestDto);
    }
    @PostMapping("/update")
    private Users updateUser(@RequestBody Users users){
        return userService.update(users);
    }
    @GetMapping("getUsers")
    private List<Users> findAll(){
        return userService.findAll();
    }

    @DeleteMapping("/deleteUser")
    private void deleteUser(@RequestParam String username){
        userService.deleteByUsername(username);
    }
}
