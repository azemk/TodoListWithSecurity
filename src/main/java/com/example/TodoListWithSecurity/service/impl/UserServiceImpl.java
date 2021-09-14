package com.example.TodoListWithSecurity.service.impl;

import com.example.TodoListWithSecurity.dto.UserRequestDto;
import com.example.TodoListWithSecurity.dto.UserResponseDto;
import com.example.TodoListWithSecurity.exceptions.CommonException;
import com.example.TodoListWithSecurity.model.Roles;
import com.example.TodoListWithSecurity.model.Users;
import com.example.TodoListWithSecurity.repository.RolesRepository;
import com.example.TodoListWithSecurity.repository.UserRepository;
import com.example.TodoListWithSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository usersRepository;
    @Autowired
    RolesRepository rolesRepository;

    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        Roles role  = rolesRepository.findRoleByName("USER");
        Users user = usersRepository.findUsersByUsername(userRequestDto.getUsername());
        if(user != null){
            throw new CommonException("User with this username already exists !");
        }else{
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            Users users = new Users();
            users.setUsername(userRequestDto.getUsername());
            users.setPassword(bCryptPasswordEncoder.encode(userRequestDto.getPassword()));
            users.setEnabled(true);
            users.setRolesList(Collections.singletonList(role));
            usersRepository.save(users);
            UserResponseDto response = UserResponseDto.builder().username(users.getUsername()).password(users.getPassword())
                    .isActive(users.getEnabled()).role(users.getRolesList().toString()).build();
            return response;
        }

    }


    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public void deleteByUsername(String username) {
        Users users = usersRepository.findUsersByUsername(username);
        if(users == null ){
            throw new CommonException("User not found!");

        }else {
            usersRepository.deleteById(users.getUsersId());
        }
    }

}
