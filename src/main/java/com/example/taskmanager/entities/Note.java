package com.example.taskmanager.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Note {
    private Integer id;
    private String title;
    private String body;

}
