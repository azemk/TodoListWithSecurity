package com.example.TodoListWithSecurity.service;

import com.example.TodoListWithSecurity.dto.TaskDto;
import com.example.TodoListWithSecurity.model.Tasks;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Tasks create(TaskDto taskDto);
    Tasks update (Long id, TaskDto taskDto);
    List<Tasks> findByUser(String username);
    void deleteById(Long id );
}
