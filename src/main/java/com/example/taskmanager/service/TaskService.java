package com.example.taskmanager.service;

import com.example.taskmanager.entities.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class TaskService {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int taskId = 1;


    public Task addTask(String title, String description, String deadline) {
        Task task = new Task();
        task.setId(taskId);
        task.setTitle(title);
       // task.setDeadline(new Date(deadline)); // TODO: validate date format YYYY-MM-DD
        task.setDescription(description);
        task.setCompleted(false);

        tasks.add(task);
        taskId++;
    return task;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }


    public Task getTaskById(int taskId) {
        for (Task tasks : tasks) {
            if (tasks.getId() == taskId) {
                return tasks;
            }
        }
        return null;
    }
}
