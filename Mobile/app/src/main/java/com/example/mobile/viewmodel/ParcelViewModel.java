package com.example.mobile.viewmodel;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.mobile.model.Parcel;
import com.example.mobile.network.ParcelApiService;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

@HiltViewModel
public class ParcelViewModel extends ViewModel {

  private static final String TAG = "ParcelViewModel";

  private final ParcelApiService parcelApiService;
  private final MutableLiveData<List<Parcel>> parcels = new MutableLiveData<>();

  @Inject
  public ParcelViewModel(ParcelApiService parcelApiService) {
    this.parcelApiService = parcelApiService;
  }

  public MutableLiveData<List<Parcel>> getParcelList() {
    return parcels;
  }

  public void fetchParcels() {
    parcelApiService.getParcels()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            result -> parcels.setValue(result),
            error -> Log.e(TAG, "getParcels: " + error.getMessage(), error));
  }
}
