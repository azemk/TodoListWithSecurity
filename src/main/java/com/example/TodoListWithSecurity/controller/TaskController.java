package com.example.TodoListWithSecurity.controller;

import com.example.TodoListWithSecurity.dto.TaskDto;
import com.example.TodoListWithSecurity.model.Tasks;
import com.example.TodoListWithSecurity.model.Users;
import com.example.TodoListWithSecurity.repository.TaskRepository;
import com.example.TodoListWithSecurity.repository.UserRepository;
import com.example.TodoListWithSecurity.service.TaskService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/myTasks")
    public String myTasks(){
        return "mytasks";
    }

    @GetMapping("/createTask")
    public String showTasksForm(Model model ,Tasks tasks) {
        model.addAttribute("newTask",tasks);
        return "create_task";
    }
    @PostMapping("/createTask")
    public String createTask(@ModelAttribute("newTask") Tasks tasks , Principal principal , BindingResult bindingResult){

        Users users = userRepository.findUsersByUsername(principal.getName());
        if(bindingResult.hasErrors()){
            return "redirect:/";
        }else if(users!= null){
            tasks.setUsers(users);
            tasks.setDateTime(LocalDateTime.now());
            taskService.create(tasks);
            return "delete_viewpage";
        }else{
            return "redirect:/";
        }
    }



    @RequestMapping  ("/deleteTask/{id}")
    public String deleteById(@PathVariable("id") Long id ){
        taskService.deleteById(id);
        return "delete_viewpage";
    }




    @GetMapping("/update/{id}")
    public String  updateForm(@PathVariable("id")  Long id  , Model model){
        Tasks task = taskRepository.findTasksById(id);
        model.addAttribute("updateTask",task);
        return "update";

    }
    @PostMapping("/update/{id}")
    public String update(Model model ,@PathVariable Long id ,  @ModelAttribute ("updateTask") Tasks task){
        try{
            task.setId(id);
            taskService.update(task);
            return "mytasks";
        }catch (Exception ex){
            String error = ex.getMessage();
            model.addAttribute("error",error);
            return "index";
        }

    }


    @GetMapping("/getTasks")
    public String getTasks(Model model ){
        TaskDto taskDto = new TaskDto();
        model.addAttribute("task" , taskDto);
        return "find_tasks";
    }


    @PostMapping ("/getTasks")
    public String getTasksForm(@NotNull Model model , String name){
        List<Tasks> taskDto = taskService.findByUser(name);
        model.addAttribute("taskList",taskDto);
        return "tasks_list";
    }






}
