package com.example.backend.controllers;

import com.example.backend.dto.NoteDto;
import com.example.backend.dto.Views;
import com.example.backend.services.NoteService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Notes", description = "Notes management APIs")
@RequestMapping(value = "notes")
@RequiredArgsConstructor
public class NoteController {

  private final NoteService noteService;

  @Operation(
      summary = "Find all Notes",
      description = "Get all Note objects.",
      tags = {"get"})
  @GetMapping
  @JsonView(Views.Get.class)
  public Collection<NoteDto> findAll() {
    log.debug("Find all notes");
    return noteService.findAll();
  }

  @Operation(
      summary = "Find a Note by Id",
      description = "Get a Note object by specifying its id.",
      tags = {"get"})
  @ApiResponse(
      responseCode = "200",
      content = {
          @Content(schema = @Schema(implementation = NoteDto.class), mediaType = "application/json")})
  @ApiResponse(
      responseCode = "404",
      content = {@Content(schema = @Schema())})
  @ApiResponse(
      responseCode = "500",
      content = {@Content(schema = @Schema())})
  @GetMapping("/{id}")
  @JsonView(value = Views.Get.class)
  public NoteDto findById(
      @Parameter(description = "Note Id.", example = "1")
      @PathVariable Long id) {
    log.debug("Find note with id: {}", id);
    return noteService.findById(id);
  }

  @Operation(
      summary = "Create a Note",
      description = "Create a Note object.",
      tags = {"post"})
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @JsonView(value = Views.Get.class)
  public NoteDto create(
      @RequestBody @JsonView(value = Views.Post.class) NoteDto note) {
    log.debug("Create note: {}", note);
    return noteService.create(note);
  }

  @Operation(
      summary = "Update a Note by Id",
      description = "Update a Note object by specifying its id.",
      tags = {"put"})
  @PutMapping("/{id}")
  @JsonView(value = Views.Get.class)
  public NoteDto update(
      @Parameter(description = "Note Id.", example = "1")
      @PathVariable Long id,
      @RequestBody @JsonView(value = Views.Put.class) NoteDto note) {
    log.debug("Update note with id: {}, with note {}", id, note);
    return noteService.update(id, note);
  }

  @Operation(
      summary = "Delete a Note by Id",
      description = "Delete a Note object by specifying its id.",
      tags = {"delete"})
  @DeleteMapping("/{id}")
  public void deleteEmployee(
      @Parameter(description = "Note Id.", example = "1")
      @PathVariable Long id) {
    log.debug("Delete note with id: {}", id);
    noteService.deleteById(id);
  }
}
