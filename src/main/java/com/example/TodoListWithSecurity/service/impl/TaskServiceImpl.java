package com.example.TodoListWithSecurity.service.impl;

import com.example.TodoListWithSecurity.dto.TaskDto;
import com.example.TodoListWithSecurity.exceptions.CommonException;
import com.example.TodoListWithSecurity.model.Tasks;
import com.example.TodoListWithSecurity.model.Users;
import com.example.TodoListWithSecurity.repository.TaskRepository;
import com.example.TodoListWithSecurity.repository.UserRepository;
import com.example.TodoListWithSecurity.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
            updateTask.setId(id);
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
        taskRepository.deleteById(id);
        throw new CommonException("User successfully deleted!");
    }

    @Override
    public TaskDto findByUser(String username) {
        Tasks tasks = taskRepository.findTasksByUsers_Username(username);
        if(tasks!= null){
            TaskDto response = TaskDto.builder().taskName(tasks.getTask_name()).description(tasks.getDescription())
                    .username(username).build();
            return response;
        }else return null;
    }
}
