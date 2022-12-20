package com.example.mobile.ui;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobile.R;
import com.example.mobile.adapters.ParcelAdapter;
import com.example.mobile.model.Parcel;
import com.example.mobile.viewmodel.ParcelViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.ArrayList;
import java.util.List;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  private List<Parcel> parcels = new ArrayList<>();
  private RecyclerView recyclerView;
  private ParcelViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    viewModel = new ViewModelProvider(this).get(ParcelViewModel.class);

    initRecyclerView();
    observeData();
    viewModel.fetchParcels();
  }

  private void initRecyclerView() {
    recyclerView = findViewById(R.id.list);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(new ParcelAdapter(parcels));
  }

  private void observeData() {
    viewModel.getParcelList().observe(this, newParcels -> {
      Log.e(TAG, "onChanged: " + newParcels.size());
      parcels = newParcels;
      recyclerView.setAdapter(new ParcelAdapter(parcels));
    });
  }
}
