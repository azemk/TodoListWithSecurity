package com.example.TodoListWithSecurity.controller;

import com.example.TodoListWithSecurity.dto.UserRequestDto;
import com.example.TodoListWithSecurity.model.Users;
import com.example.TodoListWithSecurity.repository.UserRepository;
import com.example.TodoListWithSecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.Arrays;
import java.util.List;




@Slf4j
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }


    @PostMapping("/login")
    public String login(String username , String password) {
        return "index";
    }

    @GetMapping("/register")
    public String showCreateUserForm(Model model){
        UserRequestDto userRequestDto = new UserRequestDto();
        model.addAttribute("newUser",userRequestDto);
        List<String> roles = Arrays.asList("USER","ADMIN");
        model.addAttribute("roles",roles);
        return "register_form";
    }

    @PostMapping("/register")
    public String submitForm(@ModelAttribute ("newUser") UserRequestDto userRequestDto ,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register_form";
        }
        userService.create(userRequestDto);
        return "home_page";
    }


    @PostMapping("/update")
    public Users updateUser(@RequestBody Users users){
        return userService.update(users);
    }

    @GetMapping("/getUsers")
    public String findAll(Model model){
        Iterable<Users> usersIterable = userService.findAll();
        model.addAttribute("usersList",usersIterable);
        return "users";
    }
    @GetMapping("/deleteUser")
    private String showDeleteForm(){
        return "delete_user";
    }

    @NotNull
    @PostMapping ("/deleteUser")
    private String deleteUser(ModelMap model , String username ){
        userService.deleteByUsername(username);
        return "delete";
    }

}
