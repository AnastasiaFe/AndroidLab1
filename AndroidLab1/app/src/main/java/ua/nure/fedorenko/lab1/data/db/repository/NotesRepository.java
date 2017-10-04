package ua.nure.fedorenko.lab1.data.db.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import ua.nure.fedorenko.lab1.data.NoteModel;
import ua.nure.fedorenko.lab1.data.db.exception.DBException;

public interface NotesRepository {

    void create(@NonNull NoteModel note) throws DBException;

    void update(@NonNull NoteModel note) throws DBException;

    void delete(@NonNull NoteModel note) throws DBException;

    @NonNull
    List<NoteModel> getAll();

    @Nullable
    NoteModel getById(@NonNull Long id) throws DBException;
}
