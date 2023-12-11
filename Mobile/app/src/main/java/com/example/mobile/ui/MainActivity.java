package com.example.mobile.ui;


import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobile.R;
import com.example.mobile.adapters.NoteAdapter;
import com.example.mobile.model.Note;
import com.example.mobile.viewmodel.NoteViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.ArrayList;
import java.util.List;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private List<Note> notes = new ArrayList<>();
    private RecyclerView recyclerView;
    private NoteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        initRecyclerView();
        observeData();
        viewModel.fetchNotes();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new NoteAdapter(notes));
    }

    private void observeData() {
        viewModel.getNoteList().observe(this, newNotes -> {
            Log.e(TAG, "onChanged: " + newNotes.size());
            notes = newNotes;
            recyclerView.setAdapter(new NoteAdapter(notes));
        });
    }
}