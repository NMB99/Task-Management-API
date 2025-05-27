package com.myJourney.taskManagementAPI.service;

import com.myJourney.taskManagementAPI.model.Task;
import com.myJourney.taskManagementAPI.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    // Constructor Injection
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void addTask(Task task) {
        repository.save(task);
    }

    public void updateTask(Task task) {
        repository.save(task);
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}
