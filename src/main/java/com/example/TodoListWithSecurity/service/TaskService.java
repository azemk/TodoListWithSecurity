package com.example.TodoListWithSecurity.service;

import com.example.TodoListWithSecurity.dto.TaskDto;
import com.example.TodoListWithSecurity.model.Tasks;

import java.util.List;

public interface TaskService {
    Tasks create(TaskDto taskDto);
    Tasks update (Long id, TaskDto taskDto);
    List<TaskDto> findByUser(String username);
    void deleteById(Long id );
}
