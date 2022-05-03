package com.example.todoapp.controller;

import com.example.todoapp.model.ToDoModel;
import com.example.todoapp.service.ToDoService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    private final UserDetailsService userDetailsService;
    private final ToDoService toDoService;

    public ApiController(UserDetailsService userDetailsService, ToDoService toDoService) {
        this.userDetailsService = userDetailsService;
        this.toDoService = toDoService;
    }

    @GetMapping("/user-data/{name}")
    public Collection<? extends GrantedAuthority> getUserData(@PathVariable String name) {
        return userDetailsService.loadUserByUsername(name).getAuthorities();
    }

    @GetMapping("/todos")
    public List<ToDoModel> getTodos() {
        return toDoService.getToDos();
    }
}
