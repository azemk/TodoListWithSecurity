package com.example.TodoListWithSecurity.controller;

import com.example.TodoListWithSecurity.model.Tasks;
import com.example.TodoListWithSecurity.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("getTasks")
    public List<Tasks> getAll(String username){
        return taskService.getAll(username);
    }
    @PostMapping("/createTask")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Tasks create(@RequestBody Tasks tasks){
        return taskService.create(tasks);
    }
    @PutMapping("/updateTasks")
    @PreAuthorize("hasAuthority('write')")
    public Tasks update(@PathVariable Integer id ,@RequestBody Tasks tasks){
        return taskService.update(tasks);
    }
    @DeleteMapping("/deleteTask")
    @PreAuthorize("hasAuthority('write')")
    void deleteById(@PathVariable Integer id ){
        taskService.deleteById(id);
    }





}
