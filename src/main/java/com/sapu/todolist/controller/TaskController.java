package com.sapu.todolist.controller;

import com.sapu.todolist.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.sapu.todolist.services.TaskService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @GetMapping
    public String getTasks(Model model){
        List<Task> taskList = taskService.getAllTasks();
        Collections.sort(taskList, Comparator.comparing(Task::getCompleted).reversed());
        model.addAttribute("tasks",taskList);
        return "tasks";
    }
    @PostMapping
    public String createTask(@RequestParam String title){
        Task new_task = new Task();
        new_task.setTitle(title);
        taskService.insertTask(new_task);
        return "redirect:/tasks";
    }
    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id){
        taskService.delete(id);
        return "redirect:/tasks";
    }
    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id){
        Task task = taskService.find(id);
        task.setCompleted(!task.getCompleted());
        taskService.insertTask(task);
        return "redirect:/tasks";
    }
}
