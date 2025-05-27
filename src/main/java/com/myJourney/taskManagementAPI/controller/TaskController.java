package com.myJourney.taskManagementAPI.controller;

import com.myJourney.taskManagementAPI.model.Task;

import com.myJourney.taskManagementAPI.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService service;

    // Constructor Injection
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping( "/tasks")
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping( "/task/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return service.getTaskById(id);
    }

    @PostMapping("/task")
    public void addTask(@RequestBody Task task) {
        service.addTask(task);
    }

    @PutMapping("/task/{id}")
    public void updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task task1 = service.getTaskById(id);
        if (task1 != null) {
            task.setId(id);
            service.updateTask(task);
        }
    }

    @DeleteMapping("/task/{id}")
    public void deleteTask(@PathVariable Long id) {
        Task task = service.getTaskById(id);
        if (task != null) service.deleteTask(id);
    }

}
