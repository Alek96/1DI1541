package com.example.backend.repositories.impl;

import com.example.backend.entities.Note;
import com.example.backend.repositories.NoteRepository;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import org.springframework.stereotype.Component;

@Component
public class NoteRepositoryImpl implements NoteRepository {

  private final Map<Long, Note> notes = new TreeMap<>();
  private Long maxId = 0L;

  @Override
  public Collection<Note> findAll() {
    return notes.values();
  }

  @Override
  public Note save(Note note) {
    if (note.getId() == null) {
      note.setId(maxId + 1);
    }
    maxId = Math.max(maxId, note.getId());

    notes.put(note.getId(), note);
    return note;
  }

  @Override
  public Optional<Note> findById(Long id) {
    return Optional.ofNullable(notes.get(id));
  }

  @Override
  public void deleteById(Long id) {
    notes.remove(id);
  }
}
