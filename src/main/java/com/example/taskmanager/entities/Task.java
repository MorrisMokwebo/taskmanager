package com.example.taskmanager.entities;

import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Data
public class Task {
    private int id;
    private String title;
    private String description;
    private String status;
    private Date deadline;
    private  boolean completed;

}
