package com.notes.app.service;

import com.notes.app.dto.NotesDto;
import com.notes.app.entity.Note;
import com.notes.app.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repo;

    public List<NotesDto> getAllNotes() {
        List<Note> notes = repo.findAll();
        return notes.stream().map(NotesDto::new).toList();
    }

    public NotesDto getNoteById(Long id) {
        Note note = repo.getReferenceById(id);
        return new NotesDto(note);
    }

    public NotesDto create(NotesDto notesDto) {
        Note note = new Note();
        note.setTitle(notesDto.getTitle());
        note.setContent(notesDto.getContent());
        note.setPublicVisible(notesDto.isPublicVisible());

        if (notesDto.isPublicVisible() && (notesDto.getShareId() == null || notesDto.getShareId().isBlank())) {
            notesDto.setShareId(UUID.randomUUID().toString().substring(0,8));
        }

        note.setShareId(notesDto.getShareId());
        return new NotesDto(repo.save(note));
    }

    public NotesDto update(Long id, NotesDto notesDto) {
        Note note = repo.getReferenceById(id);
        note.setTitle(notesDto.getTitle());
        note.setContent(notesDto.getContent());
        note.setPublicVisible(notesDto.isPublicVisible());
        if (note.isPublicVisible() && (note.getShareId() == null || note.getShareId().isBlank())) {
            note.setShareId(UUID.randomUUID().toString().substring(0,8));
        }
        if (!note.isPublicVisible()) {
            note.setShareId(null);
        }
        return new NotesDto(repo.save(note));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public NotesDto getByShareId(String shareId) {
        Note note = repo.findByShareId(shareId).orElseThrow();
        if (!note.isPublicVisible()) {
            throw new RuntimeException("Note is not public");
        }
        return new NotesDto(note);
    }
}
