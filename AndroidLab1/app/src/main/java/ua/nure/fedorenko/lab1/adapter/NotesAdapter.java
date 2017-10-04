package ua.nure.fedorenko.lab1.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import ua.nure.fedorenko.lab1.R;
import ua.nure.fedorenko.lab1.data.NoteModel;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<NoteModel> notes;

    private Context mContext;

    public NotesAdapter(Context context, List<NoteModel> noteModels) {
        notes = noteModels;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.note_item, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        NoteModel note = notes.get(position);
        viewHolder.nameTextView.setText(note.getName());
        viewHolder.dateTextView.setText(note.getDate().toString());
        viewHolder.noteImageView.setImageBitmap(BitmapFactory.decodeFile(note.getImagePath()));
        switch (note.getImportance()) {
            case 1:
                viewHolder.importanceImageView.setImageResource(R.drawable.button);
                break;
            case 2:
                viewHolder.importanceImageView.setImageResource(R.drawable.color);
                break;
            case 3:
                viewHolder.importanceImageView.setImageResource(R.drawable.index);
                break;
        }
        File imgFile = new File(note.getImagePath());

        if (imgFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            viewHolder.noteImageView.setImageBitmap(myBitmap);
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView nameTextView;

        public TextView dateTextView;

        public ImageView noteImageView;

        public ImageView importanceImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.noteName);
            dateTextView = (TextView) itemView.findViewById(R.id.creationDate);
            noteImageView = (ImageView) itemView.findViewById(R.id.imageIcon);
            importanceImageView = (ImageView) itemView.findViewById(R.id.importanceImageView);
        }
    }
}
