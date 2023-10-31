package com.example.taskmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UpdateTaskDTO {
    private String description;
    private String deadline;
    private Boolean completed;
}
