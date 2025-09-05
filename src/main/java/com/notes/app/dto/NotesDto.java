package com.notes.app.dto;

import com.notes.app.entity.Note;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesDto {
    private Long noteId;
    private String title;
    private String content;
    private boolean publicVisible;
    private String shareId;
    private String createdAt;
    private String updatedAt;

    public NotesDto(Note note) {
        this.noteId = note.getId();
        this.title = note.getTitle();
        this.content = note.getContent();
        this.publicVisible = note.isPublicVisible();
        this.shareId = note.getShareId();
        this.createdAt = note.getCreatedAt().toString();
        this.updatedAt = note.getUpdatedAt().toString();
    }
}
