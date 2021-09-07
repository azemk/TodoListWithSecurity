package com.example.TodoListWithSecurity.repository;

import com.example.TodoListWithSecurity.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Tasks,Long> {

    Optional<Tasks> findById(Long id);
    Tasks findTasksByUsers_Username(String username);
}
