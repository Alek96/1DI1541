package com.example.mobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobile.R;
import com.example.mobile.adapters.ParcelAdapter.ParcelViewHolder;
import com.example.mobile.model.Parcel;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParcelAdapter extends RecyclerView.Adapter<ParcelViewHolder> {

  private final List<Parcel> parcels;

  @NonNull
  @Override
  public ParcelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.parcels_list, parent, false);
    return new ParcelViewHolder(view);
  }

  // Replace the contents of a view (invoked by the layout manager)
  @Override
  public void onBindViewHolder(@NonNull ParcelViewHolder holder, int position) {
    // Get element from your dataset at this position
    // and replace the contents of the view with that element
    holder.update(parcels.get(position));
  }

  // Return the size of your dataset (invoked by the layout manager)
  @Override
  public int getItemCount() {
    return parcels.size();
  }

  /**
   * Provide a reference to the type of views that you are using
   */
  public static class ParcelViewHolder extends RecyclerView.ViewHolder {

    private final TextView textId;
    private final TextView textName;

    public ParcelViewHolder(@NonNull View view) {
      super(view);
      textId = (TextView) view.findViewById(R.id.textId);
      textName = (TextView) view.findViewById(R.id.textName);
    }

    public void update(Parcel parcel) {
      textId.setText(String.valueOf(parcel.getId()));
      textName.setText(parcel.getName());
    }
  }
}
