package com.example.todoapp.repository;

import com.example.todoapp.model.ToDoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoModel, Long> {
    Optional<ToDoModel> findByTask(String task);
    Optional<ToDoModel> findById(Long id);
    Optional<ToDoModel> findAllByUserId(Long userId);
}