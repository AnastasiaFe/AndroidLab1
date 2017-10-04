package ua.nure.fedorenko.lab1;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import ua.nure.fedorenko.lab1.data.NoteModel;
import ua.nure.fedorenko.lab1.data.db.repository.NotesDBRepository;
import ua.nure.fedorenko.lab1.data.db.repository.NotesRepository;

public class AddNoteActivity extends BaseActivity {


    private static final int PICK_IMAGE_FROM_GALLERY_CODE = 1;

    private NotesRepository repository;
    @BindView(R.id.noteNameEditText)
    EditText nameEditText;
    private String filePath;

    @BindView(R.id.noteDescriptionEditText)
    EditText descriptionEditText;

    @BindView(R.id.importanceSpinner)
    Spinner importanceSpinner;

    @BindView(R.id.uploadedImage)
    ImageView uploadedImageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        repository = new NotesDBRepository();
        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        ArrayAdapter<CharSequence> importanceAdapter = ArrayAdapter
                .createFromResource(this, R.array.importances,
                        android.R.layout.simple_spinner_item);
        importanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        importanceSpinner.setAdapter(importanceAdapter);
    }

    @OnClick(R.id.addNoteButton)
    public void onAddNoteButtonClick(View v) {
        NoteModel note = new NoteModel();
        note.setName(nameEditText.getText().toString());
        note.setDescription(descriptionEditText.getText().toString());
        note.setDate(new Date());
        note.setImportance(Integer.parseInt(String.valueOf(importanceSpinner.getSelectedItemId() + 1)));
        System.out.println(note.getImportance());
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/images" + "/" + note.getName() + ".png");
        note.setImagePath(path.getPath());
        try {
            saveImageToExternal(note.getName(), decodeFile(filePath));
            repository.create(note);
            Toast.makeText(this, repository.getAll().get(0).toString(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e("AddNoteActivity", e.getMessage(), e);
        }

    }

    @OnClick(R.id.uploadImageButton)
    public void onUploadImageButtonClick(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_FROM_GALLERY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE_FROM_GALLERY_CODE && resultCode == RESULT_OK && null != data) {
            filePath = getPath(data);
            decodeFile(filePath);
        }
    }

    private String getPath(Intent data) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }

    public Bitmap decodeFile(String filePath) {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 1024;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, o2);
        uploadedImageView.setImageBitmap(bitmap);
        return bitmap;

    }

    public void saveImageToExternal(String imgName, Bitmap bm) throws IOException {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/images");
        path.mkdirs();
        File imageFile = new File(path, imgName + ".png");
        FileOutputStream out = new FileOutputStream(imageFile);
        try {
            bm.compress(Bitmap.CompressFormat.PNG, 100, out); // Compress Image
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new IOException();
        }
    }
}
