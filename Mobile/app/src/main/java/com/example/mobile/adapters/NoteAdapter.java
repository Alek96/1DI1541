package com.example.mobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobile.R;
import com.example.mobile.adapters.NoteAdapter.NoteViewHolder;
import com.example.mobile.model.Note;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private final List<Note> notes;

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.notes_list, parent, false);
        return new NoteViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        // Get element from your dataset at this position
        // and replace the contents of the view with that element
        holder.update(notes.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return notes.size();
    }

    /**
     * Provide a reference to the type of views that you are using
     */
    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        private final TextView textId;
        private final TextView textTitle;
        private final TextView textText;
        private final TextView textCreatedBy;

        public NoteViewHolder(@NonNull View view) {
            super(view);
            textId = (TextView) view.findViewById(R.id.textId);
            textTitle = (TextView) view.findViewById(R.id.textTitle);
            textText = (TextView) view.findViewById(R.id.textText);
            textCreatedBy = (TextView) view.findViewById(R.id.textCreatedBy);
        }

        public void update(Note note) {
            textId.setText(String.valueOf(note.getId()));
            textTitle.setText(note.getTitle());
            textText.setText(note.getText());
            textCreatedBy.setText(note.getCreatedBy());
        }
    }
}
