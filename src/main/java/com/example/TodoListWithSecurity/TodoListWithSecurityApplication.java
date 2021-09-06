package com.example.TodoListWithSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@SpringBootApplication
@EnableJpaRepositories
public class TodoListWithSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListWithSecurityApplication.class, args);

	}

	@Bean(name = "datasource")
	public DataSource dataSource(){
		return DataSourceBuilder.create().build();
	}

}
