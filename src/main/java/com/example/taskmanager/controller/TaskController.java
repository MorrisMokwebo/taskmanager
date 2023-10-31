package com.example.taskmanager.controller;

import com.example.taskmanager.dto.CreateTaskDTO;
import com.example.taskmanager.dto.ErrorResponseDTO;
import com.example.taskmanager.dto.TaskResponseDTO;
import com.example.taskmanager.dto.UpdateTaskDTO;
import com.example.taskmanager.entities.Task;
import com.example.taskmanager.service.NoteService;
import com.example.taskmanager.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final NoteService noteService;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public TaskController(TaskService taskService, NoteService noteService) {
        this.taskService = taskService;
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        var tasks = taskService.getTasks();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Integer id) {
        Task task = taskService.getTaskById(id);
        var note = noteService.getNotesForTask(id);

        if( task == null){
            return ResponseEntity.notFound().build();
        }
        var taskResponse = modelMapper.map(task,TaskResponseDTO.class);
        taskResponse.setNotes(note);

        return ResponseEntity.ok(taskResponse);
    }

    @PostMapping()
    public ResponseEntity<Task> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        Task task = taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadline());

        return  ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer id, @RequestBody UpdateTaskDTO updateTaskDTO) throws ParseException {
        var task = taskService.updateTask(id,updateTaskDTO.getDescription(),updateTaskDTO.getDeadline(),updateTaskDTO.getCompleted());

        if(task == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid date format"));
        }

        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internnal server error"));
    }

}
