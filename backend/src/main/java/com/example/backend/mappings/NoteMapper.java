package com.example.backend.mappings;

import com.example.backend.dto.NoteDto;
import com.example.backend.entities.Note;
import org.mapstruct.Mapper;

@Mapper
public interface NoteMapper {

  NoteDto toDto(Note note);

  Note toEntity(NoteDto dto);
}
