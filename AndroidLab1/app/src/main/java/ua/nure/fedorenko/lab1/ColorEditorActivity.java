package ua.nure.fedorenko.lab1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import lab1.fedorenko.nure.ua.ua.R;

public class ColorEditorActivity extends AppCompatActivity {
    private int seekR;
    private int seekG;
    private int seekB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_editor);

        SeekBar sbR = (SeekBar) findViewById(R.id.redSeekBar);
        SeekBar sbG = (SeekBar) findViewById(R.id.greenSeekBar);
        SeekBar sbB = (SeekBar) findViewById(R.id.blueSeekBar);
        final LinearLayout panel = (LinearLayout) findViewById(R.id.colorPanel);
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (seekBar.getId()) {
                    case R.id.redSeekBar:
                        seekR = progress;
                        break;
                    case R.id.greenSeekBar:
                        seekG = progress;
                        break;
                    case R.id.blueSeekBar:
                        seekB = progress;
                        break;
                }
                panel.setBackgroundColor(Color.rgb(seekR, seekG, seekB));

            }
        };
        sbR.setOnSeekBarChangeListener(onSeekBarChangeListener);
        sbG.setOnSeekBarChangeListener(onSeekBarChangeListener);
        sbB.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }
}
