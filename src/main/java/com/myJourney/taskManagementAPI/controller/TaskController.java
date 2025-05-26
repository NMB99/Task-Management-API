package com.myJourney.taskManagementAPI.controller;

import com.myJourney.taskManagementAPI.model.Task;

import com.myJourney.taskManagementAPI.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
