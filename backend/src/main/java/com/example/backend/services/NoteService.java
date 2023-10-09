package com.example.backend.services;

import com.example.backend.entities.Note;
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

  private final NoteRepository noteRepo;

  public Collection<Note> findAll() {
    return noteRepo.findAll();
  }

  public Note save(Note note) {
    if (note.getId() != null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide note without id");
    }

    return noteRepo.save(note);
  }

  public Note findById(Long id) {
    return noteRepo.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found"));
  }

  public Note update(Long id, Note note) {
    if (!Objects.equals(id, note.getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id and note.id is not equal");
    }
    return noteRepo.save(note);
  }

  public void deleteById(Long id) {
    noteRepo.deleteById(id);
  }
}
