package com.example.TodoListWithSecurity.repository;

import com.example.TodoListWithSecurity.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks,Integer> {
    List<Tasks> findTasksByUsers_Username(String username);
}
