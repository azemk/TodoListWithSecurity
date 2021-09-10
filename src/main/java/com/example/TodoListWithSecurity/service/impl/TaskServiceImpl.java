package com.example.TodoListWithSecurity.service.impl;

import com.example.TodoListWithSecurity.dto.TaskDto;
import com.example.TodoListWithSecurity.exceptions.CommonException;
import com.example.TodoListWithSecurity.model.Tasks;
import com.example.TodoListWithSecurity.model.Users;
import com.example.TodoListWithSecurity.repository.TaskRepository;
import com.example.TodoListWithSecurity.repository.UserRepository;
import com.example.TodoListWithSecurity.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl  implements TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Tasks create(TaskDto taskDto) {
        Users user = userRepository.findUsersByUsername(taskDto.getUsername());
        Tasks tasks = new Tasks();
        tasks.setTask_name(taskDto.getTaskName());
        tasks.setDescription(taskDto.getDescription());
        tasks.setDateTime(LocalDateTime.now());
        tasks.setUsers(user);
        taskRepository.save(tasks);
        return tasks;
    }


    @Override
    public Tasks update(Long id,TaskDto taskDto) {
        Tasks tasks = taskRepository.getById(id);
        Users user = userRepository.findUsersByUsername(taskDto.getUsername());
        if(tasks != null){
            Tasks updateTask = new Tasks();
            updateTask.setTask_name(taskDto.getTaskName());
            updateTask.setDescription(taskDto.getDescription());
            updateTask.setDateTime(LocalDateTime.now());
            updateTask.setUsers(user);
            taskRepository.save(updateTask);
            return updateTask;
        }
        else return null;
    }


    @Override
    public void deleteById(Long id) {
        Optional<Tasks> tasks = Optional.ofNullable(taskRepository.findTasksById(id));
        if(tasks==null){
            throw new CommonException("Task not found!");
        }else
           taskRepository.deleteById(id);
        }


    @Override
    public List<Tasks> findByUser(String username) {
        List<Tasks> tasks = taskRepository.findByUsers_Username(username);
        if(tasks.size()>0){
          return tasks;
        }else {
            throw new CommonException("This user has no any tasks!");
        }
    }


}
