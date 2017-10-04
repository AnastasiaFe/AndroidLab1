package ua.nure.fedorenko.lab1.data.mapper;


import android.support.annotation.NonNull;

import ua.nure.fedorenko.lab1.data.NoteModel;
import ua.nure.fedorenko.lab1.data.db.NoteDBModel;

public class ModelToDBModelMapper implements Mapper<NoteDBModel,NoteModel>{

    @Override
    public NoteDBModel convert(@NonNull NoteModel noteModel) {
        NoteDBModel noteDBModel = new NoteDBModel();
        noteDBModel.id = noteModel.getId();
        noteDBModel.name = noteModel.getName();
        noteDBModel.description = noteModel.getDescription();
        noteDBModel.date = noteModel.getDate();
        noteDBModel.importance = noteModel.getImportance();
        noteDBModel.imagePath = noteModel.getImagePath();
        return noteDBModel;
    }
}
