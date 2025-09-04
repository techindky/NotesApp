package com.notes.app.controller;

import com.notes.app.dto.NotesDto;
import com.notes.app.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/{id}")
    public ResponseEntity<NotesDto> getNoteById(@PathVariable(name = "id") Long notesId) {
        NotesDto response = noteService.getNoteById(notesId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<NotesDto>> getAllNotes() {
        List<NotesDto> notes = noteService.getAllNotes();
        return ResponseEntity.ok(notes);
    }

    @PostMapping
    public ResponseEntity<NotesDto> create(@RequestBody NotesDto note) {
        return ResponseEntity.ok(noteService.create(note));
    }


    @PutMapping("/{id}")
    public ResponseEntity<NotesDto> update(@PathVariable Long id, @RequestBody NotesDto note) {
        return ResponseEntity.ok(noteService.update(id, note));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        noteService.delete(id);
        return ResponseEntity.ok("Deleted Successfully");
    }


    @GetMapping("/share/{id}")
    public ResponseEntity<NotesDto> share(@PathVariable(name = "id") String shareId) {
        return ResponseEntity.ok(noteService.getByShareId(shareId));
    }
}
