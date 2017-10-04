package ua.nure.fedorenko.lab1.data.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

import java.util.Date;


@Table(database = NotesDatabase.class)
public class NoteDBModel {

    @PrimaryKey
    public Long id;

    @Column
    public String name;

    @Column
    public String description;

    @Column
    public String imagePath;

    @Column
    public Integer importance;

    @Column
    public Date date;
}
