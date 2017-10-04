package ua.nure.fedorenko.lab1;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import butterknife.BindView;


public class ColorEditorActivity extends BaseActivity {

    @BindView(R.id.redSeekBar)
    SeekBar redSeekBar;

    @BindView(R.id.greenSeekBar)
    SeekBar greenSeekBar;

    @BindView(R.id.blueSeekBar)
    SeekBar blueSeekBar;

    @BindView(R.id.colorPanel)
    LinearLayout panel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_editor);
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //implementation is not needed
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //implementation is not needed
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int color = Color.rgb(redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress());
                panel.setBackgroundColor(color);
            }
        };
        redSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        greenSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        blueSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }
}
