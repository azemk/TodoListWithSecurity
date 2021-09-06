package com.example.TodoListWithSecurity.service.impl;

import com.example.TodoListWithSecurity.model.Tasks;
import com.example.TodoListWithSecurity.repository.TaskRepository;
import com.example.TodoListWithSecurity.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl  implements TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Override
    public Tasks create(Tasks task) {
        return taskRepository.save(task);
    }

    @Override
    public Tasks update(Tasks task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Tasks> getAll(String username) {
        List<Tasks> tasks = taskRepository.findTasksByUsers_Username(username);
        return tasks;
    }

    @Override
    public void deleteById(Integer id) {
        taskRepository.deleteById(id);
    }
}
