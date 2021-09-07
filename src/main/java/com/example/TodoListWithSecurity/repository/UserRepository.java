package com.example.TodoListWithSecurity.repository;

import com.example.TodoListWithSecurity.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;


public interface UserRepository extends JpaRepository<Users,Long> {
     Users findUsersByUsername(String username);
}
