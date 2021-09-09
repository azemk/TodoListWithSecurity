package com.example.TodoListWithSecurity.security;


import com.example.TodoListWithSecurity.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity

public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {



    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/hello","/","/home").permitAll()
                .antMatchers(HttpMethod.GET,"/getUsers").hasAuthority("ADMIN")
                .antMatchers("/register").permitAll()
                .antMatchers("/create").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.POST,"/createTask").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.DELETE,"/delete_user").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/updateTask").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.DELETE,"/deleteTask").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/getTasks").hasAnyAuthority("USER","ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .httpBasic()
                .and().csrf().disable();

    }




}
