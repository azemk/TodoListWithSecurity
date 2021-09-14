package com.example.TodoListWithSecurity.service.impl;

import com.example.TodoListWithSecurity.exceptions.CommonException;
import com.example.TodoListWithSecurity.model.Tasks;
import com.example.TodoListWithSecurity.model.Users;
import com.example.TodoListWithSecurity.repository.TaskRepository;
import com.example.TodoListWithSecurity.repository.UserRepository;
import com.example.TodoListWithSecurity.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class TaskServiceImpl  implements TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Tasks create(Tasks tasks ) {
        Users user = userRepository.findUsersByUsername(tasks.getUsers().getUsername());
        if(user!=null){
            Tasks newTask = new Tasks();
            newTask.setTask_name(tasks.getTask_name());
            newTask.setDescription(tasks.getDescription());
            newTask.setDateTime(LocalDateTime.now());
            newTask.setUsers(user);
            taskRepository.save(tasks);
            return tasks;
        }
        else{
            throw new CommonException("This user does not exists");
        }

    }


    @Override
    public Tasks update(Tasks tasks) {
        Tasks task = taskRepository.getById(tasks.getId());
        Users user = userRepository.findUsersByUsername(task.getUsers().getUsername());
        if(task != null){
            tasks.setTask_name(tasks.getTask_name());
            tasks.setDescription(tasks.getDescription());
            tasks.setDateTime(LocalDateTime.now());
            tasks.setUsers(user);
            taskRepository.save(tasks);
            return tasks;
        }
        else return null;
    }


    @Override
    public void deleteById(Long id) {
        Optional<Tasks> tasks = Optional.ofNullable(taskRepository.findTasksById(id));
        if(tasks!=null){
            taskRepository.deleteById(id);
        }else{
            throw new CommonException("Task not found!");
        }
    }


    @Override
    public List<Tasks> findByUser(String username) {
        List<Tasks> tasks = taskRepository.findByUsers_Username(username);
        if(tasks.size()>0){
            return tasks;
        }else return null;
    }


}
