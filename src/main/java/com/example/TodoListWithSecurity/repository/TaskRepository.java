package com.example.TodoListWithSecurity.repository;

import com.example.TodoListWithSecurity.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Tasks,Long> {

    Tasks findTasksById(Long id);
    List<Tasks> findByUsers_Username(String username);

}
