package com.example.backend.services;

import com.example.backend.entities.Parcel;
import com.example.backend.repositories.ParcelRepository;
import java.util.Collection;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ParcelService {

  private final ParcelRepository parcelRepo;

  public Collection<Parcel> findAll() {
    return parcelRepo.findAll();
  }

  public Parcel save(Parcel parcel) {
    if (parcel.getId() != null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide parcel without id");
    }

    return parcelRepo.save(parcel);
  }

  public Parcel findById(Long id) {
    return parcelRepo.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parcel Not Found"));
  }

  public Parcel update(Long id, Parcel parcel) {
    if (!Objects.equals(id, parcel.getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id and parcel.id is not equal");
    }
    return parcelRepo.save(parcel);
  }

  public void deleteById(Long id) {
    parcelRepo.deleteById(id);
  }
}
