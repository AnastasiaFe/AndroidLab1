package ua.nure.fedorenko.lab1.data.mapper;


import android.support.annotation.NonNull;

import ua.nure.fedorenko.lab1.data.NoteModel;
import ua.nure.fedorenko.lab1.data.db.NoteDBModel;

public class DBModelToModelMapper implements Mapper<NoteModel, NoteDBModel> {

    @Override
    @NonNull
    public NoteModel convert(@NonNull NoteDBModel noteDBModel) {
        NoteModel note = new NoteModel();
        note.setId(noteDBModel.id);
        note.setName(noteDBModel.name);
        note.setDescription(noteDBModel.description);
        note.setDate(noteDBModel.date);
        note.setImportance(noteDBModel.importance);
        note.setImagePath(noteDBModel.imagePath);
        return note;
    }
}
