package com.example.TodoListWithSecurity.repository;

import com.example.TodoListWithSecurity.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<Users, Long> {
    Users findUsersByUsername(String username);
}
