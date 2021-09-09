package com.example.TodoListWithSecurity.controller;

import com.example.TodoListWithSecurity.dto.TaskDto;
import com.example.TodoListWithSecurity.model.Tasks;
import com.example.TodoListWithSecurity.service.TaskService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/createTask")
    public String showTasksForm(Model model) {
        TaskDto taskDto = new TaskDto();
        model.addAttribute("task",taskDto);
        return "task";
    }
    @PostMapping("/createTask")
    public String createTask(@ModelAttribute("task") TaskDto taskDto){
        taskService.create(taskDto);
        return "task";
    }

    @GetMapping("/deleteTask")
    public String deleteTaskForm(){
        return "delete_task";
    }

    @PostMapping("/deleteTask")
    public String deleteById(Long id ){
        taskService.deleteById(id);
        return "delete";
    }

    @GetMapping("/getTasks")
    public String getTasks(Model model ){
        TaskDto taskDto = new TaskDto();
        model.addAttribute("task" , taskDto);
        return "all_tasks";
    }


    @PostMapping ("/getTasks")
    public String getTasksForm(@NotNull Model model , String username){
        List<TaskDto> taskDto = taskService.findByUser(username);
        model.addAttribute("taskList",taskDto);
        return "task_list";
    }



    @PostMapping("/updateTask")
    public Tasks update(@RequestParam Long id ,@RequestBody TaskDto taskDto){
        return taskService.update(id,taskDto);
    }








}
