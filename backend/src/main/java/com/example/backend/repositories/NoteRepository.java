package com.example.backend.repositories;

import com.example.backend.entities.Note;
import java.util.Collection;
import java.util.Optional;

public interface NoteRepository {

  Collection<Note> findAll();

  Note save(Note note);

  Optional<Note> findById(Long id);

  void deleteById(Long id);
}
