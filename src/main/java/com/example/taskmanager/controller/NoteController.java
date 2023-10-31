package com.example.taskmanager.controller;

import com.example.taskmanager.dto.CreateNoteDTO;
import com.example.taskmanager.dto.CreateNoteResponseDTO;
import com.example.taskmanager.entities.Note;
import com.example.taskmanager.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping()
    public ResponseEntity<List<Note>> getNotes(@PathVariable Integer taskId){
        var notes = noteService.getNotesForTask(taskId);

        return ResponseEntity.ok(notes);

    }

    @PostMapping()
    public ResponseEntity<CreateNoteResponseDTO> addNote(@PathVariable Integer taskId, @RequestBody CreateNoteDTO body){
        var note = noteService.addNoteForTask(taskId,body.getTitle(),body.getBody());

        return  ResponseEntity.ok(new CreateNoteResponseDTO(taskId,note));

    }
}
