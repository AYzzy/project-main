package org.example.dtos.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateNoteResponse {
    private String noteId;
    private String title;
    private String body;
    private String dateCreated;

}
