package com.example.TodoListWithSecurity.controller;

import com.example.TodoListWithSecurity.dto.TaskDto;
import com.example.TodoListWithSecurity.model.Tasks;
import com.example.TodoListWithSecurity.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class TaskController {
    @Autowired
    TaskService taskService;




    @PostMapping("/createTask")

    public Tasks create(@RequestBody TaskDto taskDto){
        return taskService.create(taskDto);
    }


    @DeleteMapping("/deleteTask")
    void deleteById(@RequestParam Long id ){
        taskService.deleteById(id);
    }

    @GetMapping("/getTasks")
    public TaskDto getTasks(@RequestParam String username){
        return taskService.findByUser(username);
    }
    @PostMapping("/updateTask")
    public Tasks update(@RequestParam Long id ,@RequestBody TaskDto taskDto){
        return taskService.update(id,taskDto);
    }





}
