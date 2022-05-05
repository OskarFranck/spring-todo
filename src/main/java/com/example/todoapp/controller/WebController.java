package com.example.todoapp.controller;

import com.example.todoapp.model.ToDoModel;
import com.example.todoapp.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final ToDoService toDoService;
    private final UserDetailsService userDetailsService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/role")
    public String checkRole() {
        return "role";
    }

    @GetMapping("/todos")
    public String toDos(Model model) {
        model.addAttribute("todos", toDoService.getToDos());
        return "todos";
    }

    @GetMapping("/user-todos/{id}")
    public String userTodos(@PathVariable(name = "id") Long userId, Model model) {
        model.addAttribute("userTodos", toDoService.getToDoForUser(userId));
        return "user-todos";
    }

    @GetMapping("/create-todo")
    public String todo (Model model) {
        model.addAttribute("todo", new ToDoModel());
        return "create-todo";
    }

    @PostMapping("/create-todo")
    public String createTodo(@ModelAttribute ToDoModel toDoModel, Model model) {
        model.addAttribute("todo", toDoService.createToDo(toDoModel));
        return "todo-created";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable(name = "id") Long id) {
        toDoService.deleteTodo(id);
        return "redirect:/todos";
    }

    @PostMapping("/update/{id}")
    public String TodoUpdated(@PathVariable(name = "id") Long id, @ModelAttribute ToDoModel toDoModel, Model model) {
        model.addAttribute("todo", toDoService.updateTodo(id, toDoModel));
        return "redirect:/todos";
    }

    @GetMapping("/edit/{id}")
    public String UpdateTodo(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("todo", toDoService.getToDoById(id));
        return "update-todo";
    }

}
