package ua.nure.fedorenko.lab1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import butterknife.BindView;

public class AddNoteActivity extends BaseActivity {

    @BindView(R.id.noteNameEditText)
    EditText nameEditText;

    @BindView(R.id.noteDescriptionEditText)
    EditText descriptionEditText;

    @BindView(R.id.importanceSpinner)
    Spinner importanceSpinner;

    @BindView(R.id.uploadImageButton)
    Button uploadImageButton;

    @BindView(R.id.uploadedImage)
    ImageView uploadedImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ArrayAdapter<CharSequence> importanceAdapter = ArrayAdapter
                .createFromResource(this, R.array.importances,
                        android.R.layout.simple_spinner_item);
        importanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        importanceSpinner.setAdapter(importanceAdapter);
    }
}
