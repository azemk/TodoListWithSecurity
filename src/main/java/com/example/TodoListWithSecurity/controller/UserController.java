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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Arrays;
import java.util.List;




@Slf4j
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/home_page")
    public String home() {
        return "home_page";
    }


    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/register")
    public String showCreateUserForm(Model model) {
        UserRequestDto userRequestDto = new UserRequestDto();
        model.addAttribute("user", userRequestDto);
        List<String> roles = Arrays.asList("USER", "ADMIN");
        model.addAttribute("roles", roles);
        return "register_form";
    }

    @PostMapping("/register")
    public String submitForm(@ModelAttribute("newUser") UserRequestDto userRequestDto, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "register_form";
        }
        userService.create(userRequestDto);
        return "viewpage";
    }





    @GetMapping("/getUsers")
    public String findAll(Model model){
        Iterable<Users> usersIterable = userService.findAll();
        model.addAttribute("usersList",usersIterable);
        return "users_list";
    }
    @GetMapping("/deleteUser")
    private String showDeleteForm(){
        return "delete_user";
    }

    @PostMapping ("/deleteUser")
    private String deleteUser( String username ){
        userService.deleteByUsername(username);
        return "delete_viewpage";
    }

}
