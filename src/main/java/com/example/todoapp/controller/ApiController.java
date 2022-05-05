package com.example.todoapp.controller;

import com.example.todoapp.model.ToDoModel;
import com.example.todoapp.service.ToDoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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
    public UserDetails getUserData(@PathVariable String name) {
        return userDetailsService.loadUserByUsername(name);
    }

    @GetMapping("/todos")
    public List<ToDoModel> getTodos() {
        return toDoService.getToDos();
    }

    @PutMapping(value = "/update-todo",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ToDoModel updateTodo(@RequestBody ToDoModel task) {
        return toDoService.updateTodo(task.getId() , task);
    }

    @DeleteMapping(value = "/delete",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void deleteTodo(@RequestBody ToDoModel todo) {
        toDoService.deleteTodo(todo.getId());
    }
}
