package com.example.todoapp.service;

import com.example.todoapp.model.ToDoModel;
import com.example.todoapp.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoModel getToDoByTask(String task) {
        Optional<ToDoModel> optionalToDoModel = toDoRepository.findByTask(task);
        return optionalToDoModel.map(toDoModel -> new ToDoModel(toDoModel.getTask())).orElse(null);
    }

    public ToDoModel getToDoById(Long id) {
        Optional<ToDoModel> optionalToDoModel = toDoRepository.findById(id);
        return optionalToDoModel.map(toDoModel -> new ToDoModel(toDoModel.getTask())).orElse(null);
    }

    public ToDoModel createToDo(ToDoModel toDo) {
        return toDoRepository.save(toDo);
    }

    public List<ToDoModel> getToDos() {
        return toDoRepository.findAll();
    }

    public Boolean completeTodo(Long id) {
        Boolean status = toDoRepository.getById(id).getCompleted();
        System.out.println(status);
        ToDoModel todo = toDoRepository.getById(id);
        todo.setCompleted(!status);
        System.out.println(todo.getCompleted());
        return status;
    }

    public Optional<ToDoModel> getToDoForUser(Long userId) {
        return toDoRepository.findAllByUserId(userId);
    }

    public ToDoModel updateTodo(Long id, ToDoModel toDoModel) {
        Optional<ToDoModel> optionalToDoModel = toDoRepository.findById(id);
        ToDoModel existingTodo = optionalToDoModel.map(todo -> new ToDoModel(todo.getTask())).orElse(null);
        if (existingTodo == null) throw new Error("gg");
        existingTodo.setId(toDoModel.getId());
        if (toDoModel.getTitle().isEmpty()) {
            existingTodo.setTitle(optionalToDoModel.get().getTitle());
        } else {
            existingTodo.setTitle(toDoModel.getTitle());
        }
        if (toDoModel.getTask().isEmpty()) {
            existingTodo.setTask(optionalToDoModel.get().getTask());
        } else {
            existingTodo.setTask(toDoModel.getTask());
        }
        toDoRepository.save(existingTodo);
        return existingTodo;
    }

    public void deleteTodo(Long id) {
        toDoRepository.deleteById(id);
    }
}