package com.example.TodoListWithSecurity.controller;

import com.example.TodoListWithSecurity.dto.UserRequestDto;
import com.example.TodoListWithSecurity.exceptions.CommonException;

import com.example.TodoListWithSecurity.model.Users;
import com.example.TodoListWithSecurity.repository.RolesRepository;
import com.example.TodoListWithSecurity.repository.UserRepository;
import com.example.TodoListWithSecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Arrays;
import java.util.List;




@Slf4j
@Controller
@EnableWebMvc
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    RolesRepository rolesRepository;

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


    @RequestMapping  ("/deleteUser/{id}")
    private String deleteUser( @PathVariable ("id") Long id ){
        userRepository.deleteById(id);
        return "delete_viewpage";
    }


    @GetMapping("/updateUser/{id}")
    public String updateForm(@PathVariable("id") Long id , Model model){
        Users user = userRepository.findUsersByUsersId(id);
        model.addAttribute("user",user);
        return "update_user";
    }

    @PostMapping("/updateUser/{id}")
    public String update(@ModelAttribute("user") Users users , @PathVariable("id") Long id ,Model model  ){
        try{
            Users updated = userRepository.findUsersByUsersId(id);
            if(updated!=null){
                updated.setUsername(users.getUsername());
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
                updated.setPassword(encoder.encode(users.getPassword()));
                updated.setEnabled(true);
                updated.setRolesList(rolesRepository.findRolesByName("USER"));
                userRepository.save(updated);
                return "update_user";
            }else{
                throw new CommonException("User with this id not found!");
            }
        }catch (Exception ex){
           String message = ex.getMessage() + ex.getCause();
           model.addAttribute("error" , message);
           return "error";
        }

    }

}
