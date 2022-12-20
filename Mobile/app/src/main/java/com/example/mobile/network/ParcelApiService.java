package com.example.mobile.network;

import com.example.mobile.model.Parcel;
import io.reactivex.rxjava3.core.Observable;
import java.util.List;
import retrofit2.http.GET;

public interface ParcelApiService {

  @GET("parcels")
  Observable<List<Parcel>> getParcels();
}
