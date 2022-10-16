package com.example.backend.repositories;

import com.example.backend.entities.Parcel;
import java.util.Collection;
import java.util.Optional;

public interface ParcelRepository {

  Collection<Parcel> findAll();

  Parcel save(Parcel parcel);

  Optional<Parcel> findById(Long id);

  void deleteById(Long id);
}
