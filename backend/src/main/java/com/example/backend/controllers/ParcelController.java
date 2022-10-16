package com.example.backend.controllers;

import com.example.backend.entities.Parcel;
import com.example.backend.services.ParcelService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "parcels",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ParcelController {

  private final ParcelService parcelService;

  @GetMapping
  Collection<Parcel> findAll() {
    log.debug("Find all parcels");
    return parcelService.findAll();
  }

  @PostMapping
  Parcel create(@RequestBody Parcel parcel) {
    log.debug("Create parcel: {}", parcel);
    return parcelService.save(parcel);
  }

  @GetMapping("/{id}")
  Parcel findById(@PathVariable Long id) {
    log.debug("Find parcel with id: {}", id);
    return parcelService.findById(id);
  }

  @PutMapping("/{id}")
  Parcel update(@PathVariable Long id, @RequestBody Parcel parcel) {
    log.debug("Update parcel with id: {}, with parcel {}", id, parcel);
    return parcelService.update(id, parcel);
  }

  @DeleteMapping("/{id}")
  void deleteEmployee(@PathVariable Long id) {
    log.debug("Delete parcel with id: {}", id);
    parcelService.deleteById(id);
  }
}
