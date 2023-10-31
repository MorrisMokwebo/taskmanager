package com.example.taskmanager.entities;

import lombok.Data;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
public class Task {
    private int id;
    private String title;
    private String description;
    private Date deadline;
    private  boolean completed;

}
