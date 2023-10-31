package com.example.taskmanager.service;

import com.example.taskmanager.entities.Task;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class TaskService {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int taskId = 1;
    private final SimpleDateFormat deadlineFormat = new SimpleDateFormat("yyyy-MM-dd");


    public Task addTask(String title, String description, String deadline) throws ParseException {
        Task task = new Task();
        task.setId(taskId);
        task.setTitle(title);
       task.setDeadline(deadlineFormat.parse(deadline));
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

    public Task updateTask(Integer id, String description, String deadline,Boolean completed) throws ParseException {
        Task task = getTaskById(id);

        if(task == null){
            return null;
        }

        if(description != null){
            task.setDescription(description);
        }

        if(deadline != null){
            task.setDeadline(deadlineFormat.parse(deadline));
        }

        if(completed != null){
            task.setCompleted(completed);
        }

        return  task;
    }
}
