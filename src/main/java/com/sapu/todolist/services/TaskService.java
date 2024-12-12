package com.sapu.todolist.services;

import com.sapu.todolist.models.Task;
import com.sapu.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public void insertTask(Task task){
        taskRepository.save(task);
    }
    public void delete(Long id){
        taskRepository.deleteById(id);
    }
    public Task find(Long id){
        return taskRepository.findById(id).get();
    }
}
