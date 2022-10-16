package com.example.backend.repositories.imp;

import com.example.backend.entities.Parcel;
import com.example.backend.repositories.ParcelRepository;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import org.springframework.stereotype.Component;

@Component
public class ParcelRepositoryImpl implements ParcelRepository {

  private final Map<Long, Parcel> parcels = new TreeMap<>();
  private Long maxId = 0L;

  @Override
  public Collection<Parcel> findAll() {
    return parcels.values();
  }

  @Override
  public Parcel save(Parcel parcel) {
    if (parcel.getId() == null) {
      parcel.setId(maxId + 1);
    }
    maxId = Math.max(maxId, parcel.getId());

    parcels.put(parcel.getId(), parcel);
    return parcel;
  }

  @Override
  public Optional<Parcel> findById(Long id) {
    return Optional.ofNullable(parcels.get(id));
  }

  @Override
  public void deleteById(Long id) {
    parcels.remove(id);
  }
}
