package com.example.activity.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.activity.Task;
import com.example.activity.repositories.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task save(Task task){
        return taskRepository.save(task);
    }

    public Optional<Task> findById(Long id){
        return taskRepository.findById(id);
    }

    public void deleteById(Long id){
        taskRepository.deleteById(id);
    }
}
