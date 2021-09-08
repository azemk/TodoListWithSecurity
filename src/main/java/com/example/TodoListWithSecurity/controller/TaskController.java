package com.example.TodoListWithSecurity.controller;

import com.example.TodoListWithSecurity.dto.TaskDto;
import com.example.TodoListWithSecurity.model.Tasks;
import com.example.TodoListWithSecurity.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
    public String getTasks(@RequestParam String username , Model model){
        TaskDto tasks = taskService.findByUser(username);
        model.addAttribute("task",new TaskDto());
        return "index.html";
    }
    @PostMapping("/updateTask")
    public Tasks update(@RequestParam Long id ,@RequestBody TaskDto taskDto){
        return taskService.update(id,taskDto);
    }








}
