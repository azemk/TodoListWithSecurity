package com.example.TodoListWithSecurity.service;

import com.example.TodoListWithSecurity.model.Tasks;

import java.util.List;

public interface TaskService {
    Tasks create(Tasks tasks );
    Tasks update (Tasks tasks);
    List<Tasks> findByUser(String username);
    void deleteById(Long id );

}
