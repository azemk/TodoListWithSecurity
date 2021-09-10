package com.example.TodoListWithSecurity.controller;

import com.example.TodoListWithSecurity.dto.TaskDto;
import com.example.TodoListWithSecurity.model.Tasks;
import com.example.TodoListWithSecurity.repository.TaskRepository;
import com.example.TodoListWithSecurity.service.TaskService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

public class TaskController {
    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/myTasks")
    public String myTasks(){
        return "mytasks";
    }

    @GetMapping("/createTask")
    public String showTasksForm(Model model) {
        TaskDto taskDto = new TaskDto();
        model.addAttribute("task",taskDto);
        return "create_task";
    }
    @PostMapping("/createTask")
    public String createTask(@ModelAttribute("task") TaskDto taskDto){
        taskService.create(taskDto);
        return "create_task";
    }

    @GetMapping("/deleteTask")
    public String deleteTaskForm(){
        return "delete_task";
    }

    @PostMapping("/deleteTask")
    public String deleteById(Long id ){
        taskService.deleteById(id);
        return "delete_viewpage";
    }

    @GetMapping("/getTasks")
    public String getTasks(Model model ){
        TaskDto taskDto = new TaskDto();
        model.addAttribute("task" , taskDto);
        return "find_tasks";
    }


    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable("id")  Long id ){
        ModelAndView modelAndView = new ModelAndView("update");
        Tasks tasks = taskRepository.findTasksById(id);
        modelAndView.addObject("tasks",tasks);
        return modelAndView;

    }
    @RequestMapping("/save")
    public String save(@ModelAttribute("tasks") Tasks tasks){
        return taskService.
    }


    @PostMapping ("/getTasks")
    public String getTasksForm(@NotNull Model model , String username){
        List<Tasks> tasks = taskService.findByUser(username);
        model.addAttribute("taskList",tasks);
        return "tasks_list";
    }


    @PostMapping("/updateTask")
    public Tasks update(@PathVariable Long id ,@RequestBody TaskDto taskDto){
        return taskService.update(id,taskDto);
    }



}
