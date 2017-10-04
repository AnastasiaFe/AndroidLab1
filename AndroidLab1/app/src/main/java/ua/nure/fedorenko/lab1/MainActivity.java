package ua.nure.fedorenko.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.calculatorButton)
    public void calculatorButtonClick(View v) {
        startActivity(new Intent(MainActivity.this, CalculatorActivity.class));
    }

    @OnClick(R.id.colorButton)
    public void colorButtonClick(View v) {
        startActivity(new Intent(MainActivity.this, ColorEditorActivity.class));
    }
}
