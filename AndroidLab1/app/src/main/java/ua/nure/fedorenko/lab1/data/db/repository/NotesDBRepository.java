package ua.nure.fedorenko.lab1.data.db.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import java.util.ArrayList;
import java.util.List;

import ua.nure.fedorenko.lab1.data.NoteModel;
import ua.nure.fedorenko.lab1.data.db.NoteDBModel;
import ua.nure.fedorenko.lab1.data.db.NoteDBModel_Table;
import ua.nure.fedorenko.lab1.data.db.exception.DBException;
import ua.nure.fedorenko.lab1.data.mapper.DBModelToModelMapper;
import ua.nure.fedorenko.lab1.data.mapper.Mapper;
import ua.nure.fedorenko.lab1.data.mapper.ModelToDBModelMapper;

public class NotesDBRepository implements NotesRepository {

    private final ModelAdapter<NoteDBModel> adapter;
    private final Mapper<NoteDBModel, NoteModel> modelToDBModelMapper;
    private final Mapper<NoteModel, NoteDBModel> dbModelToModelMapper;

    public NotesDBRepository() {
        adapter = FlowManager.getModelAdapter(NoteDBModel.class);
        modelToDBModelMapper = new ModelToDBModelMapper();
        dbModelToModelMapper = new DBModelToModelMapper();
    }

    @Override
    public void create(@NonNull NoteModel note) throws DBException {
        long rowsAffected = adapter.insert(modelToDBModelMapper.convert(note));
        if (rowsAffected == 0) {
            throw new DBException("Error during adding of note!");
        }
    }

    @Override
    public void update(@NonNull NoteModel note) throws DBException {
        boolean isUpdated = adapter.update(modelToDBModelMapper.convert(note));
        if (!isUpdated) {
            throw new DBException("Error during updating of note!");
        }
    }

    @Override
    public void delete(@NonNull NoteModel note) throws DBException {
        boolean isDeleted = adapter.delete(modelToDBModelMapper.convert(note));
        if (!isDeleted) {
            throw new DBException("Error during deleting of note!");
        }
    }

    @NonNull
    @Override
    public List<NoteModel> getAll() {
        List<NoteDBModel> noteDBModels = SQLite.select()
                .from(NoteDBModel.class)
                .queryList();
        List<NoteModel> models = new ArrayList<>();
        for (NoteDBModel dbModel : noteDBModels) {
            models.add(dbModelToModelMapper.convert(dbModel));
        }
        return models;
    }

    @Nullable
    @Override
    public NoteModel getById(@NonNull Long id) throws DBException {
        NoteDBModel dbModel = SQLite.select()
                .from(NoteDBModel.class)
                .where(NoteDBModel_Table.id.is(id))
                .querySingle();
        if (dbModel != null) {
            return dbModelToModelMapper.convert(dbModel);
        } else {
            throw new DBException("Error during getting note by id!");
        }
    }
}
