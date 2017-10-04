package ua.nure.fedorenko.lab1;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import butterknife.BindView;


public class ColorEditorActivity extends BaseActivity {
    private int redSeekBarProgress;
    private int greenSeekBarProgress;
    private int blueSeekBarProgress;
    @BindView(R.id.redSeekBar)
    SeekBar sbR;

    @BindView(R.id.greenSeekBar)
    SeekBar sbG;

    @BindView(R.id.blueSeekBar)
    SeekBar sbB;

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
                switch (seekBar.getId()) {
                    case R.id.redSeekBar:
                        redSeekBarProgress = progress;
                        break;
                    case R.id.greenSeekBar:
                        greenSeekBarProgress = progress;
                        break;
                    case R.id.blueSeekBar:
                        blueSeekBarProgress = progress;
                        break;
                }
                panel.setBackgroundColor(Color.rgb(redSeekBarProgress, greenSeekBarProgress, blueSeekBarProgress));
            }
        };
        sbR.setOnSeekBarChangeListener(onSeekBarChangeListener);
        sbG.setOnSeekBarChangeListener(onSeekBarChangeListener);
        sbB.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }
}
