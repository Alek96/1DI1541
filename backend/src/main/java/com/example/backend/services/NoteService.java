package com.example.backend.services;

import com.example.backend.dto.NoteDto;
import com.example.backend.entities.Note;
import com.example.backend.mappings.NoteMapper;
import com.example.backend.repositories.NoteRepository;
import java.util.Collection;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class NoteService {

  private final NoteMapper noteMapper;
  private final NoteRepository noteRepo;

  public Collection<NoteDto> findAll() {
    return noteRepo.findAll().stream()
        .map(noteMapper::toDto)
        .toList();
  }

  public NoteDto findById(Long id) {
    return noteRepo.findById(id)
        .map(noteMapper::toDto)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found"));
  }

  public NoteDto create(NoteDto note) {
    if (note.getId() != null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide note without id");
    }

    return save(note);
  }

  public NoteDto update(Long id, NoteDto note) {
    if (!Objects.equals(id, note.getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id and note.id is not equal");
    }
    return save(note);
  }

  public NoteDto save(NoteDto note) {
    Note entity = noteMapper.toEntity(note);
    entity = noteRepo.saveAndFlush(entity);
    return noteMapper.toDto(entity);
  }

  public void deleteById(Long id) {
    noteRepo.deleteById(id);
  }
}
