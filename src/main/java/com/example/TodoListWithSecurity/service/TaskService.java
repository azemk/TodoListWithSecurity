package com.example.TodoListWithSecurity.service;

import com.example.TodoListWithSecurity.model.Tasks;

import java.util.List;

public interface TaskService {
    public Tasks create(Tasks task);
    public Tasks update (Tasks task);
    public List<Tasks> getAll(String username);
    void deleteById(Integer id );
}
