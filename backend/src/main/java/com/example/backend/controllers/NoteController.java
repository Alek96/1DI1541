package com.example.backend.controllers;

import com.example.backend.entities.Note;
import com.example.backend.services.NoteService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "notes")
@RequiredArgsConstructor
public class NoteController {

  private final NoteService noteService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  Note create(@RequestBody Note note) {
    log.debug("Create note: {}", note);
    return noteService.save(note);
  }

  @GetMapping
  Collection<Note> findAll() {
    log.debug("Find all notes");
    return noteService.findAll();
  }

  @GetMapping("/{id}")
  Note findById(@PathVariable Long id) {
    log.debug("Find note with id: {}", id);
    return noteService.findById(id);
  }

  @PutMapping("/{id}")
  Note update(@PathVariable Long id, @RequestBody Note note) {
    log.debug("Update note with id: {}, with note {}", id, note);
    return noteService.update(id, note);
  }

  @DeleteMapping("/{id}")
  void deleteEmployee(@PathVariable Long id) {
    log.debug("Delete note with id: {}", id);
    noteService.deleteById(id);
  }
}
