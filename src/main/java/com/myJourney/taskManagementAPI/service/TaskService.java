package com.myJourney.taskManagementAPI.service;

import com.myJourney.taskManagementAPI.model.Task;
import com.myJourney.taskManagementAPI.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Task addTask(Task task) {
        task.setId(null);
        return repository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        if (repository.existsById(id)) {
            task.setId(id);
            return repository.save(task);
        }
        return null;
    }

    public boolean deleteTask(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
