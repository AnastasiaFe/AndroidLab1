package ua.nure.fedorenko.lab1.data.db.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import ua.nure.fedorenko.lab1.data.NoteModel;

public interface NotesRepository {

    void create(@NonNull NoteModel note);

    void update(@NonNull NoteModel note);

    void delete(@NonNull NoteModel note);

    @NonNull
    List<NoteModel> getAll();

    @Nullable
    NoteModel getById(@NonNull Long id);
}
