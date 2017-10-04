package ua.nure.fedorenko.lab1;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import ua.nure.fedorenko.lab1.adapter.NotesAdapter;
import ua.nure.fedorenko.lab1.data.NoteModel;
import ua.nure.fedorenko.lab1.data.db.repository.NotesDBRepository;
import ua.nure.fedorenko.lab1.data.db.repository.NotesRepository;

public class NotesActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView notesRecycleView;
    List<NoteModel> notes;

    private NotesRepository repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        repository = new NotesDBRepository();
        notes = repository.getAll();
        NotesAdapter adapter = new NotesAdapter(this, notes);
        notesRecycleView.setAdapter(adapter);
        notesRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notes_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNoteMenuItem:
                startActivity(new Intent(NotesActivity.this, AddNoteActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
