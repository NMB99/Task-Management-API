package com.myJourney.taskManagementAPI.controller;

import com.myJourney.taskManagementAPI.model.Task;

import com.myJourney.taskManagementAPI.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService service;

    // Constructor Injection
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping( "/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = service.getAllTasks();
        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tasks);
    }

    @GetMapping( "/task/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = service.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/task")
    public ResponseEntity<?> addTask(@RequestBody Task task) {
        Task newTask = service.addTask(task);
        if (newTask != null) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of(
                            "message", "Task added successfully",
                            "task", newTask
                    ));
        }
        return ResponseEntity.badRequest().body(Map.of("error", "Task could not be added"));
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updated = service.updateTask(id, task);
        if (updated != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Map.of(
                            "message", "Task updated successfully",
                            "task", updated
                    ));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        boolean deleted = service.deleteTask(id);
        if (deleted) {
            return ResponseEntity.ok(Map.of("message", "Task deleted successfully"));
        }
        return ResponseEntity.notFound().build();
    }

}
